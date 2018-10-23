<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/9/27
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_content">
        <div class="x_title">
            <h3>
                在建工程（土建）申报 &nbsp;&nbsp;&nbsp;&nbsp;
                <small>
                    <div class="btn-group">
                        <button type="button" class="btn btn-success" onclick="civilEngineering.showAddModel();"
                                data-toggle="modal"> 新增
                        </button>
                    </div>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">导入土建
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a class="btn"
                                   onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftDeclareBuildEngineering)">下载模板</a>
                            </li>
                            <li><a class="btn btn-default" onclick="$('#civilEngineeringUpload').val('').trigger('click')">导入</a>
                            </li>
                        </ul>
                    </div>
                </small>
            </h3>
            <div class="clearfix"></div>
        </div>
        <form class="form-horizontal">
            <div class="form-group">
                <div class="x-valid">
                    <table class="table table-bordered" id="civilEngineeringTableList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>
<input type="file" id="civilEngineeringUpload" name="file" style="display: none;"
       onchange="civilEngineering.inputFile();">
<script>
    /**
     * @author:  zch
     * 描述:配置一些必须的参数
     * @date:2018-09-27
     **/
    var civilEngineeringConfig = {
        name: "土建",
        frm: "civilEngineeringFrm",
        table: "civilEngineeringTableList",
        box: "civilEngineeringBox",
        fileId: "civilEngineeringFileId",
        excelUpload: "civilEngineeringUpload",
        declareBuildingConstructionPermit: {
            box: "declareBuildingConstructionPermitBox",
            frm: "declareBuildingConstructionPermitFrm",
            fileId: "declareBuildingConstructionPermitFileId",
            name: "建筑工程施工许可证"
        },
        declarePreSalePermit: {
            box: "declarePreSalePermitBox",
            frm: "declarePreSalePermitFrm",
            fileId: "declarePreSalePermitFileId",
            name: "商品房预售许可证"
        },
        declareLandUsePermit: {
            box: "declareLandUsePermitBox",
            frm: "declareLandUsePermitFrm",
            fileId: "declareLandUsePermitFileId",
            name: "建设用地规划许可证"
        },
        declareBuildingPermit: {
            box: "declareBuildingPermitBox",
            frm: "declareBuildingPermitFrm",
            fileId: "declareBuildingPermitFileId",
            name: "建设工程规划许可证"
        },
        declareRealtyLandCert: {
            box: "declareRealtyLandCertBox",
            frm: "declareRealtyLandCertFrm",
            fileId: "declareRealtyLandCertFileId",
            name: "土地证"
        },
        declareRealtyRealEstateCert: {
            box: "declareRealtyRealEstateCertBox",
            frm: "declareRealtyRealEstateCertFrm",
            fileId: "declareRealtyRealEstateCertFileId",
            name: "不动产"
        }
    };

    var civilEngineering = new Object();

    //处理标识符的地方-------start
    civilEngineering.civilEngineeringFlag = true;
    civilEngineering.declareRealtyLandCertFlag = true;
    civilEngineering.declareRealtyRealEstateCertFlag = true;
    //----------------------end

    civilEngineering.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    civilEngineering.objectWriteSelectData = function (frm, data, name) {
        if (civilEngineering.isEmpty(data)) {
            $("#" + frm + " ." + name).val(data).trigger("change");
        } else {
            $("#" + frm + " ." + name).val(null).trigger("change");
        }
    };

    civilEngineering.showFile = function (target, tableName, id) {
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

    civilEngineering.fileUpload = function (target, tableName, id) {
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
            }, onUploadComplete: function (result, file) {
                civilEngineering.showFile(target, tableName, id);
            },
            deleteFlag: true
        });
    };

    civilEngineering.init = function () {
        AssessCommon.initAreaInfo({
            provinceTarget: $("#" + civilEngineeringConfig.frm + "province"),
            cityTarget: $("#" + civilEngineeringConfig.frm + "city"),
            districtTarget: $("#" + civilEngineeringConfig.frm + "district"),
            provinceValue: '',
            cityValue: '',
            districtValue: ''
        });
    };

    /**
     * @author:  zch
     * 描述:在建工程（土建）显示
     * @date:2018-09-27
     **/
    civilEngineering.showAddModel = function () {
        $("#" + civilEngineeringConfig.frm).clearAll();
        if (civilEngineering.civilEngineeringFlag) {
            civilEngineering.init();
            civilEngineering.civilEngineeringFlag = false;
        }
        civilEngineering.showFile(civilEngineeringConfig.fileId, AssessDBKey.DeclareBuildEngineering, 0);
        civilEngineering.fileUpload(civilEngineeringConfig.fileId, AssessDBKey.DeclareBuildEngineering, 0);
        //使校验生效
        $("#" + civilEngineeringConfig.frm).validate();
        $('#' + civilEngineeringConfig.box).modal("show");
    };

    /**
     * @author:  zch
     * 描述:在建工程（土建）删除
     * @date:2018-09-27
     **/
    civilEngineering.deleteData = function (id) {
        Alert("是否删除",2,null,function () {
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/declareBuildEngineering/deleteDeclareBuildEngineeringById",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        civilEngineering.loadList();
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

    /**
     * @author:  zch
     * 描述:在建工程（土建）编辑
     * @date:2018-09-27
     **/
    civilEngineering.editData = function (id) {
        $("#" + civilEngineeringConfig.frm).clearAll();
        if (civilEngineering.civilEngineeringFlag) {
            civilEngineering.init();
            civilEngineering.civilEngineeringFlag = false;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/declareBuildEngineering/getDeclareBuildEngineeringById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    if (civilEngineering.isEmpty(data)) {
                        $("#" + civilEngineeringConfig.frm).initForm(result.data);
                        civilEngineering.showFile(civilEngineeringConfig.fileId, AssessDBKey.DeclareBuildEngineering, data.id);
                        civilEngineering.fileUpload(civilEngineeringConfig.fileId, AssessDBKey.DeclareBuildEngineering, data.id);
                        $("#" + civilEngineeringConfig.frm + " input[name='declarationDate']").val(formatDate(data.declarationDate));
                        $("#" + civilEngineeringConfig.frm + " input[name='expectedCompletionDate']").val(formatDate(data.expectedCompletionDate));
                        $("#" + civilEngineeringConfig.frm + " input[name='startDate']").val(formatDate(data.startDate));
                        AssessCommon.initAreaInfo({
                            provinceTarget: $("#" + civilEngineeringConfig.frm + "province"),
                            cityTarget: $("#" + civilEngineeringConfig.frm + "city"),
                            districtTarget: $("#" + civilEngineeringConfig.frm + "district"),
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
        $("#" + civilEngineeringConfig.frm).validate();
        $('#' + civilEngineeringConfig.box).modal("show");
    };

    /**
     * @author:  zch
     * 描述:在建工程（土建）更新
     * @date:2018-09-27
     **/
    civilEngineering.saveAndUpdateData = function () {
        if (!$("#" + civilEngineeringConfig.frm).valid()) {
            return false;
        }
        var data = formParams(civilEngineeringConfig.frm);
        data.planDetailsId = '${empty projectPlanDetails.id?0:projectPlanDetails.id}';
        data.declareType = declareFunObj.getDeclareType("土建");
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareBuildEngineering/saveAndUpdateDeclareBuildEngineering",
            data: data,
            success: function (result) {
                if (result.ret) {
                    civilEngineering.loadList();
                    toastr.success('成功!');
                    $('#' + civilEngineeringConfig.box).modal("hide");
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
     * 描述:更新绑定到父类上的子类
     * @date:2018-09-28
     **/
    civilEngineering.handleFather = function (item) {
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
            url: "${pageContext.request.contextPath}/declareBuildEngineering/saveAndUpdateDeclareBuildEngineering",
            data: item,
            success: function (result) {
                if (result.ret) {
                    civilEngineering.loadList();
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
    civilEngineering.declareRealtyRealEstateCertRoleBeLocated = {
        init: function () {
            $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='unit']").blur(function () {
                if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='unit']").val())) {
                    civilEngineering.declareRealtyRealEstateCertRoleBeLocated.write();
                }
            });
            $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='floor']").blur(function () {
                if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='floor']").val())) {
                    civilEngineering.declareRealtyRealEstateCertRoleBeLocated.write();
                }
            });
            $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='roomNumber']").blur(function () {
                if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='roomNumber']").val())) {
                    civilEngineering.declareRealtyRealEstateCertRoleBeLocated.write();
                }
            });
            $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='streetNumber']").blur(function () {
                if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='streetNumber']").val())) {
                    civilEngineering.declareRealtyRealEstateCertRoleBeLocated.write();
                }
            });
            $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='attachedNumber']").blur(function () {
                if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='attachedNumber']").val())) {
                    civilEngineering.declareRealtyRealEstateCertRoleBeLocated.write();
                }
            });
            $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='buildingNumber']").blur(function () {
                if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='buildingNumber']").val())) {
                    civilEngineering.declareRealtyRealEstateCertRoleBeLocated.write();
                }
            });
            $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " .district").change(function () {
                /**
                 * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
                 **/
                var id = $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " .district").eq(1).val();
                if (civilEngineering.isEmpty(id)) {
                    civilEngineering.declareRealtyRealEstateCertRoleBeLocated.write();
                }
            });
        },
        write: function () {
            var temp = "";
            var district = $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " .district").eq(1).val();
            var unit = $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='unit']").val();
            var floor = $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='floor']").val();
            var roomNumber = $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='roomNumber']").val();
            var streetNumber = $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='streetNumber']").val();
            var attachedNumber = $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='attachedNumber']").val();
            var buildingNumber = $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='buildingNumber']").val();
            if (!civilEngineering.isEmpty(unit)) {
                unit = "";
            } else {
                unit = unit + "单元";
            }
            if (!civilEngineering.isEmpty(floor)) {
                floor = "";
            } else {
                floor = floor + "楼";
            }
            if (!civilEngineering.isEmpty(roomNumber)) {
                roomNumber = "";
            } else {
                roomNumber = roomNumber + "号";
            }
            if (!civilEngineering.isEmpty(streetNumber)) {
                streetNumber = "";
            }
            if (!civilEngineering.isEmpty(attachedNumber)) {
                attachedNumber = "";
            } else {
                attachedNumber =  "附"+attachedNumber;
            }
            if (!civilEngineering.isEmpty(buildingNumber)) {
                buildingNumber = "";
            } else {
                buildingNumber = buildingNumber + "栋";
            }
            if (civilEngineering.isEmpty(district)) {
                AssessCommon.getAreaById(district, function (data) {
                    if (!civilEngineering.isEmpty(data)) {
                        district = "";
                    } else {
                        district = data.name;
                    }
                    temp = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                    $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='beLocated']").val(temp);
                });
            } else {
                district = "";
                temp = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='beLocated']").val(temp);
            }
        }
    };
    civilEngineering.declareRealtyRealEstateCertRoleCertName = {
        init: function () {
            $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='location']").blur(function () {
                if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='location']").val())) {
                    civilEngineering.declareRealtyRealEstateCertRoleCertName.write();
                }
            });
            $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='number']").blur(function () {
                if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='number']").val())) {
                    civilEngineering.declareRealtyRealEstateCertRoleCertName.write();
                }
            });
            $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " .type").change(function () {
                /**
                 * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
                 **/
                var id = $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " .type").eq(1).val();
                if (civilEngineering.isEmpty(id)) {
                    civilEngineering.declareRealtyRealEstateCertRoleCertName.write();
                }
            });
        },
        write: function () {
            var location = $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='location']").val();
            var number = $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='number']").val();
            var id = $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " .type").eq(1).val();
            if (!civilEngineering.isEmpty(location)) {
                location = "";
            }
            if (!civilEngineering.isEmpty(number)) {
                number = "";
            }
            if (!civilEngineering.isEmpty(id)) {
                id = "";
            }
            if (civilEngineering.isEmpty(id)) {
                AssessCommon.getProjectClassifyInfo(id, function (data) {
                    if (civilEngineering.isEmpty(data)) {
                        var temp = location + "房权证" + data.name + "字地" + number + "号";
                        $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='certName']").val(temp);
                    }
                });
            } else {
                var temp = location + "房权证" + id + "字地" + number + "号";
                $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='certName']").val(temp);
            }
        }
    };
    civilEngineering.declareRealtyRealEstateCertInit = function () {
        AssessCommon.initAreaInfo({
            provinceTarget: $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + "province"),
            cityTarget: $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + "city"),
            districtTarget: $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + "district"),
            provinceValue: '',
            cityValue: '',
            districtValue: ''
        });
        AssessCommon.getProjectClassifyListByFieldName(AssessProjectClassifyKey.singleLandPropertyCertificateTypeCategory, function (html, data) {
            $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm).find('select.type').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, "", function (html, data) {
            $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm).find('select.purpose').empty().html(html).trigger('change');
        });
        civilEngineering.declareRealtyRealEstateCertRoleCertName.init();
        civilEngineering.declareRealtyRealEstateCertRoleBeLocated.init();
    };
    civilEngineering.declareRealtyRealEstateCertView = function (id) {
        if (civilEngineering.declareRealtyRealEstateCertFlag) {
            civilEngineering.declareRealtyRealEstateCertInit();
            civilEngineering.declareRealtyRealEstateCertFlag = false;
        }
        var item = $("#" + civilEngineeringConfig.table).bootstrapTable('getRowByUniqueId', id);
        var realEstateId = item.realEstateId;
        $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm).clearAll();
        if (civilEngineering.isEmpty(realEstateId)) {
            $.ajax({
                url: "${pageContext.request.contextPath}/declareRealtyRealEstateCert/getDeclareRealtyRealEstateCertById",
                type: "get",
                dataType: "json",
                data: {id: realEstateId},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (civilEngineering.isEmpty(data)) {
                            data.pidC = id;
                            $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm).initForm(data);
                            civilEngineering.objectWriteSelectData(civilEngineeringConfig.declareRealtyRealEstateCert.frm, data.purpose, "purpose");
                            civilEngineering.objectWriteSelectData(civilEngineeringConfig.declareRealtyRealEstateCert.frm, data.type, "type");
                            civilEngineering.objectWriteSelectData(civilEngineeringConfig.declareRealtyRealEstateCert.frm, data.useRightType, "useRightType");
                            $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='terminationDate']").val(formatDate(data.terminationDate));
                            $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='useEndDate']").val(formatDate(data.useEndDate));
                            $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='useStartDate']").val(formatDate(data.useStartDate));
                            $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='registrationTime']").val(formatDate(data.registrationTime));
                            $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + " input[name='registrationDate']").val(formatDate(data.registrationDate));
                            civilEngineering.showFile(civilEngineeringConfig.declareRealtyRealEstateCert.fileId, AssessDBKey.DeclareRealtyRealEstateCert, data.id);
                            civilEngineering.fileUpload(civilEngineeringConfig.declareRealtyRealEstateCert.fileId, AssessDBKey.DeclareRealtyRealEstateCert, data.id);
                            AssessCommon.initAreaInfo({
                                provinceTarget: $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + "province"),
                                cityTarget: $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + "city"),
                                districtTarget: $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm + "district"),
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
        } else {
            $("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm).initForm({pidC: id});
            civilEngineering.showFile(civilEngineeringConfig.declareRealtyRealEstateCert.fileId, AssessDBKey.DeclareRealtyRealEstateCert, 0);
            civilEngineering.fileUpload(civilEngineeringConfig.declareRealtyRealEstateCert.fileId, AssessDBKey.DeclareRealtyRealEstateCert, 0);
        }
        $('#' + civilEngineeringConfig.declareRealtyRealEstateCert.box).modal("show");
    };
    civilEngineering.declareRealtyRealEstateCertSaveAndUpdate = function () {
        if (!$("#" + civilEngineeringConfig.declareRealtyRealEstateCert.frm).valid()) {
            return false;
        }
        var data = formParams(civilEngineeringConfig.declareRealtyRealEstateCert.frm);
        <%--data.planDetailsId = '${empty projectPlanDetails.id?0:projectPlanDetails.id}';--%>
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareRealtyRealEstateCert/saveAndUpdateDeclareRealtyRealEstateCert",
            data: data,
            success: function (result) {
                if (result.ret) {
                    $('#' + civilEngineeringConfig.declareRealtyRealEstateCert.box).modal("hide");
                    var item = result.data;
                    if (civilEngineering.isEmpty(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                        var pidC = data.pidC;
                        var fData = $("#" + civilEngineeringConfig.table).bootstrapTable('getRowByUniqueId', pidC);
                        fData.realEstateId = item;
                        civilEngineering.handleFather(fData);
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
     * 描述:土地证
     * @date:2018-09-28
     **/
    civilEngineering.declareRealtyLandCertCertRoleBeLocated = {
        init: function () {
            $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='unit']").blur(function () {
                if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='unit']").val())) {
                    civilEngineering.declareRealtyLandCertCertRoleBeLocated.write();
                }
            });
            $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='floor']").blur(function () {
                if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='floor']").val())) {
                    civilEngineering.declareRealtyLandCertCertRoleBeLocated.write();
                }
            });
            $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='roomNumber']").blur(function () {
                if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='roomNumber']").val())) {
                    civilEngineering.declareRealtyLandCertCertRoleBeLocated.write();
                }
            });
            $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='streetNumber']").blur(function () {
                if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='streetNumber']").val())) {
                    civilEngineering.declareRealtyLandCertCertRoleBeLocated.write();
                }
            });
            $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='attachedNumber']").blur(function () {
                if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='attachedNumber']").val())) {
                    civilEngineering.declareRealtyLandCertCertRoleBeLocated.write();
                }
            });
            $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='buildingNumber']").blur(function () {
                if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='buildingNumber']").val())) {
                    civilEngineering.declareRealtyLandCertCertRoleBeLocated.write();
                }
            });
            $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " .district").change(function () {
                /**
                 * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
                 **/
                var id = $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " .district").eq(1).val();
                if (civilEngineering.isEmpty(id)) {
                    civilEngineering.declareRealtyLandCertCertRoleBeLocated.write();
                }
            });
        },
        write: function () {
            var temp = null;
            var district = $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " .district").eq(1).val();
            var unit = $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='unit']").val();
            var floor = $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='floor']").val();
            var roomNumber = $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='roomNumber']").val();
            var streetNumber = $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='streetNumber']").val();
            var attachedNumber = $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='attachedNumber']").val();
            var buildingNumber = $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='buildingNumber']").val();
            if (!civilEngineering.isEmpty(unit)) {
                unit = "";
            } else {
                unit = unit + "单元";
            }
            if (!civilEngineering.isEmpty(floor)) {
                floor = "";
            } else {
                floor = floor + "楼";
            }
            if (!civilEngineering.isEmpty(roomNumber)) {
                roomNumber = "";
            } else {
                roomNumber = roomNumber + "号";
            }
            if (!civilEngineering.isEmpty(streetNumber)) {
                streetNumber = "";
            }
            if (!civilEngineering.isEmpty(attachedNumber)) {
                attachedNumber = "";
            } else {
                attachedNumber ="附"+attachedNumber;
            }
            if (!civilEngineering.isEmpty(buildingNumber)) {
                buildingNumber = "";
            } else {
                buildingNumber = buildingNumber + "栋";
            }
            if (civilEngineering.isEmpty(district)) {
                AssessCommon.getAreaById(district, function (data) {
                    if (!civilEngineering.isEmpty(data)) {
                        district = "";
                    } else {
                        district = data.name;
                    }
                    temp = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                    $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='beLocated']").val(temp);
                });
            } else {
                district = "";
                temp = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='beLocated']").val(temp);
            }
        }
    };
    civilEngineering.declareRealtyLandCertCertRoleCertName = {
        init: function () {
            $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='location']").blur(function () {
                if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='location']").val())) {
                    civilEngineering.declareRealtyLandCertCertRoleCertName.write();
                }
            });
            $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='year']").blur(function () {
                if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='year']").val())) {
                    civilEngineering.declareRealtyLandCertCertRoleCertName.write();
                }
            });
            $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='number']").blur(function () {
                if (civilEngineering.isEmpty($("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='number']").val())) {
                    civilEngineering.declareRealtyLandCertCertRoleCertName.write();
                }
            });
            $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " .type").change(function () {
                /**
                 * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
                 **/
                var id = $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " .type").eq(1).val();
                if (civilEngineering.isEmpty(id)) {
                    civilEngineering.declareRealtyLandCertCertRoleCertName.write();
                }
            });
        },
        write: function () {
            var temp = "";
            var id = $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " .type").eq(1).val();
            var location = $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='location']").val();
            var year = $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='year']").val();
            var number = $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='number']").val();
            if (civilEngineering.isEmpty(id)) {
                AssessCommon.getProjectClassifyInfo(id, function (data) {
                    if (civilEngineering.isEmpty(data)) {
                        var temp = location + data.name + year + "第" + number + "号";
                        $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='landCertName']").val(temp);
                    }
                });
            } else {
                var temp = location + year + "第" + number + "号";
                $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='landCertName']").val(temp);
            }
        }
    };
    civilEngineering.declareRealtyLandCertInit = function () {
        AssessCommon.initAreaInfo({
            provinceTarget: $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + "province"),
            cityTarget: $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + "city"),
            districtTarget: $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + "district"),
            provinceValue: '',
            cityValue: '',
            districtValue: ''
        });
        AssessCommon.getProjectClassifyListByFieldName(AssessProjectClassifyKey.singleLandPropertyCertificateTypeCategory, function (html, data) {
            $("#" + civilEngineeringConfig.declareRealtyLandCert.frm).find('select.type').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, "", function (html, data) {
            $("#" + civilEngineeringConfig.declareRealtyLandCert.frm).find('select.purpose').empty().html(html).trigger('change');
        });
        civilEngineering.declareRealtyLandCertCertRoleCertName.init();
        civilEngineering.declareRealtyLandCertCertRoleBeLocated.init();
    };
    civilEngineering.declareRealtyLandCertView = function (id) {
        if (civilEngineering.declareRealtyLandCertFlag) {
            civilEngineering.declareRealtyLandCertInit();
            civilEngineering.declareRealtyLandCertFlag = false;
        }
        var item = $("#" + civilEngineeringConfig.table).bootstrapTable('getRowByUniqueId', id);
        var landId = item.landId;
        $("#" + civilEngineeringConfig.declareRealtyLandCert.frm).clearAll();
        if (civilEngineering.isEmpty(landId)) {
            $.ajax({
                url: "${pageContext.request.contextPath}/declareRealtyLandCert/getDeclareRealtyLandCertById",
                type: "get",
                dataType: "json",
                data: {id: landId},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (civilEngineering.isEmpty(data)) {
                            data.pidC = id;
                            $("#" + civilEngineeringConfig.declareRealtyLandCert.frm).initForm(data);
                            civilEngineering.objectWriteSelectData(civilEngineeringConfig.declareRealtyLandCert.frm, data.purpose, "purpose");
                            civilEngineering.objectWriteSelectData(civilEngineeringConfig.declareRealtyLandCert.frm, data.type, "type");
                            civilEngineering.objectWriteSelectData(civilEngineeringConfig.declareRealtyLandCert.frm, data.useRightType, "useRightType");
                            $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='terminationDate']").val(formatDate(data.terminationDate));
                            $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + " input[name='registrationDate']").val(formatDate(data.registrationDate));
                            civilEngineering.showFile(civilEngineeringConfig.declareRealtyLandCert.fileId, AssessDBKey.DeclareRealtyLandCert, data.id);
                            civilEngineering.fileUpload(civilEngineeringConfig.declareRealtyLandCert.fileId, AssessDBKey.DeclareRealtyLandCert, data.id);
                            AssessCommon.initAreaInfo({
                                provinceTarget: $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + "province"),
                                cityTarget: $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + "city"),
                                districtTarget: $("#" + civilEngineeringConfig.declareRealtyLandCert.frm + "district"),
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
        } else {
            $("#" + civilEngineeringConfig.declareRealtyLandCert.frm).initForm({pidC: id});
            civilEngineering.showFile(civilEngineeringConfig.declareRealtyLandCert.fileId, AssessDBKey.DeclareRealtyLandCert, 0);
            civilEngineering.fileUpload(civilEngineeringConfig.declareRealtyLandCert.fileId, AssessDBKey.DeclareRealtyLandCert, 0);
        }
        $('#' + civilEngineeringConfig.declareRealtyLandCert.box).modal("show");
    };
    civilEngineering.declareRealtyLandCertSaveAndUpdate = function () {
        if (!$("#" + civilEngineeringConfig.declareRealtyLandCert.frm).valid()) {
            return false;
        }
        var data = formParams(civilEngineeringConfig.declareRealtyLandCert.frm);
        <%--data.planDetailsId = '${empty projectPlanDetails.id?0:projectPlanDetails.id}';--%>
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareRealtyLandCert/saveAndUpdateDeclareRealtyLandCert",
            data: data,
            success: function (result) {
                if (result.ret) {
                    $('#' + civilEngineeringConfig.declareRealtyLandCert.box).modal("hide");
                    var item = result.data;
                    if (civilEngineering.isEmpty(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                        var pidC = data.pidC;
                        var fData = $("#" + civilEngineeringConfig.table).bootstrapTable('getRowByUniqueId', pidC);
                        fData.landId = item;
                        civilEngineering.handleFather(fData);
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
     * 描述:建设工程规划许可证 view
     * @date:2018-09-28
     **/
    civilEngineering.declareBuildingPermitView = function (id) {
        var item = $("#" + civilEngineeringConfig.table).bootstrapTable('getRowByUniqueId', id);
        $("#" + civilEngineeringConfig.declareBuildingPermit.frm).clearAll();
        var buildingPermitId = item.buildingPermitId;
        if (civilEngineering.isEmpty(buildingPermitId)) {
            $.ajax({
                url: "${pageContext.request.contextPath}/declareBuildingPermit/getDeclareBuildingPermitById",
                type: "get",
                dataType: "json",
                data: {id: buildingPermitId},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (civilEngineering.isEmpty(data)) {
                            data.pid = id;
                            $("#" + civilEngineeringConfig.declareBuildingPermit.frm).initForm(data);
                            $("#" + civilEngineeringConfig.declareBuildingPermit.frm + " input[name='date']").val(formatDate(data.date));
                            civilEngineering.showFile(civilEngineeringConfig.declareBuildingPermit.fileId, AssessDBKey.DeclareBuildingPermit, data.id);
                            civilEngineering.fileUpload(civilEngineeringConfig.declareBuildingPermit.fileId, AssessDBKey.DeclareBuildingPermit, data.id);
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        } else {
            $("#" + civilEngineeringConfig.declareBuildingPermit.frm).initForm({pid: id});
            civilEngineering.showFile(civilEngineeringConfig.declareBuildingPermit.fileId, AssessDBKey.DeclareBuildingPermit, 0);
            civilEngineering.fileUpload(civilEngineeringConfig.declareBuildingPermit.fileId, AssessDBKey.DeclareBuildingPermit, 0);
        }
        $('#' + civilEngineeringConfig.declareBuildingPermit.box).modal("show");
    };

    /**
     * @author:  zch
     * 描述:建设工程规划许可证 更新
     * @date:2018-09-28
     **/
    civilEngineering.declareBuildingPermitSaveAndUpdate = function () {
        if (!$("#" + civilEngineeringConfig.declareBuildingPermit.frm).valid()) {
            return false;
        }
        var data = formParams(civilEngineeringConfig.declareBuildingPermit.frm);
        <%--data.planDetailsId = '${empty projectPlanDetails.id?0:projectPlanDetails.id}';--%>
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareBuildingPermit/saveAndUpdateDeclareBuildingPermit",
            data: data,
            success: function (result) {
                if (result.ret) {
                    $('#' + civilEngineeringConfig.declareBuildingPermit.box).modal("hide");
                    var item = result.data;
                    if (civilEngineering.isEmpty(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                        var pid = data.pid;
                        var fData = $("#" + civilEngineeringConfig.table).bootstrapTable('getRowByUniqueId', pid);
                        fData.buildingPermitId = item;
                        civilEngineering.handleFather(fData);
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
    civilEngineering.declareLandUsePermitView = function (id) {
        var item = $("#" + civilEngineeringConfig.table).bootstrapTable('getRowByUniqueId', id);
        $("#" + civilEngineeringConfig.declareLandUsePermit.frm).clearAll();
        var landUsePermitId = item.landUsePermitId;
        if (civilEngineering.isEmpty(landUsePermitId)) {
            $.ajax({
                url: "${pageContext.request.contextPath}/declareLandUsePermit/getDeclareLandUsePermitById",
                type: "get",
                dataType: "json",
                data: {id: landUsePermitId},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (civilEngineering.isEmpty(data)) {
                            data.pid = id;
                            $("#" + civilEngineeringConfig.declareLandUsePermit.frm).initForm(data);
                            $("#" + civilEngineeringConfig.declareLandUsePermit.frm + " input[name='date']").val(formatDate(data.date));
                            civilEngineering.showFile(civilEngineeringConfig.declareLandUsePermit.fileId, AssessDBKey.DeclareLandUsePermit, data.id);
                            civilEngineering.fileUpload(civilEngineeringConfig.declareLandUsePermit.fileId, AssessDBKey.DeclareLandUsePermit, data.id);
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        } else {
            $("#" + civilEngineeringConfig.declareLandUsePermit.frm).initForm({pid: id});
            civilEngineering.showFile(civilEngineeringConfig.declareLandUsePermit.fileId, AssessDBKey.DeclareLandUsePermit, 0);
            civilEngineering.fileUpload(civilEngineeringConfig.declareLandUsePermit.fileId, AssessDBKey.DeclareLandUsePermit, 0);
        }
        $('#' + civilEngineeringConfig.declareLandUsePermit.box).modal("show");
    };

    /**
     * @author:  zch
     * 描述:建设用地规划许可证 更新
     * @date:2018-09-28
     **/
    civilEngineering.declareLandUsePermitSaveAndUpdate = function () {
        if (!$("#" + civilEngineeringConfig.declareLandUsePermit.frm).valid()) {
            return false;
        }
        var data = formParams(civilEngineeringConfig.declareLandUsePermit.frm);
        <%--data.planDetailsId = '${empty projectPlanDetails.id?0:projectPlanDetails.id}';--%>
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareLandUsePermit/saveAndUpdateDeclareLandUsePermit",
            data: data,
            success: function (result) {
                if (result.ret) {
                    $('#' + civilEngineeringConfig.declareLandUsePermit.box).modal("hide");
                    var item = result.data;
                    if (civilEngineering.isEmpty(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                        var pid = data.pid;
                        var fData = $("#" + civilEngineeringConfig.table).bootstrapTable('getRowByUniqueId', pid);
                        fData.landUsePermitId = item;
                        civilEngineering.handleFather(fData);
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
    civilEngineering.declarePreSalePermitView = function (id) {
        var item = $("#" + civilEngineeringConfig.table).bootstrapTable('getRowByUniqueId', id);
        $("#" + civilEngineeringConfig.declarePreSalePermit.frm).clearAll();
        var preSalePermitId = item.preSalePermitId;
        if (civilEngineering.isEmpty(preSalePermitId)) {
            $.ajax({
                url: "${pageContext.request.contextPath}/declarePreSalePermit/getDeclarePreSalePermitById",
                type: "get",
                dataType: "json",
                data: {id: preSalePermitId},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (civilEngineering.isEmpty(data)) {
                            data.pid = id;
                            $("#" + civilEngineeringConfig.declarePreSalePermit.frm).initForm(data);
                            $("#" + civilEngineeringConfig.declarePreSalePermit.frm + " input[name='date']").val(formatDate(data.date));
                            civilEngineering.showFile(civilEngineeringConfig.declarePreSalePermit.fileId, AssessDBKey.DeclarePreSalePermit, data.id);
                            civilEngineering.fileUpload(civilEngineeringConfig.declarePreSalePermit.fileId, AssessDBKey.DeclarePreSalePermit, data.id);
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        } else {
            $("#" + civilEngineeringConfig.declarePreSalePermit.frm).initForm({pid: id});
            civilEngineering.showFile(civilEngineeringConfig.declarePreSalePermit.fileId, AssessDBKey.DeclarePreSalePermit, 0);
            civilEngineering.fileUpload(civilEngineeringConfig.declarePreSalePermit.fileId, AssessDBKey.DeclarePreSalePermit, 0);
        }
        $('#' + civilEngineeringConfig.declarePreSalePermit.box).modal("show");
    };

    /**
     * @author:  zch
     * 描述:商品房预售许可证 更新
     * @date:2018-09-28
     **/
    civilEngineering.declarePreSalePermitSaveAndUpdate = function () {
        if (!$("#" + civilEngineeringConfig.declarePreSalePermit.frm).valid()) {
            return false;
        }
        var data = formParams(civilEngineeringConfig.declarePreSalePermit.frm);
        <%--data.planDetailsId = '${empty projectPlanDetails.id?0:projectPlanDetails.id}';--%>
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/declarePreSalePermit/saveAndUpdateDeclarePreSalePermit",
            data: data,
            success: function (result) {
                if (result.ret) {
                    $('#' + civilEngineeringConfig.declarePreSalePermit.box).modal("hide");
                    var item = result.data;
                    if (civilEngineering.isEmpty(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                        var pid = data.pid;
                        var fData = $("#" + civilEngineeringConfig.table).bootstrapTable('getRowByUniqueId', pid);
                        fData.preSalePermitId = item;
                        civilEngineering.handleFather(fData);
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
    civilEngineering.declareBuildingConstructionPermitView = function (id) {
        var item = $("#" + civilEngineeringConfig.table).bootstrapTable('getRowByUniqueId', id);
        $("#" + civilEngineeringConfig.declareBuildingConstructionPermit.frm).clearAll();
        var buildingConstructionPermitId = item.buildingConstructionPermitId;
        if (civilEngineering.isEmpty(buildingConstructionPermitId)) {
            $.ajax({
                url: "${pageContext.request.contextPath}/declareBuildingConstructionPermit/getDeclareBuildingConstructionPermitById",
                type: "get",
                dataType: "json",
                data: {id: buildingConstructionPermitId},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (civilEngineering.isEmpty(data)) {
                            data.pid = id;
                            $("#" + civilEngineeringConfig.declareBuildingConstructionPermit.frm).initForm(data);
                            $("#" + civilEngineeringConfig.declareBuildingConstructionPermit.frm + " input[name='contractPeriod']").val(formatDate(data.contractPeriod));
                            $("#" + civilEngineeringConfig.declareBuildingConstructionPermit.frm + " input[name='date']").val(formatDate(data.date));
                            civilEngineering.showFile(civilEngineeringConfig.declareBuildingConstructionPermit.fileId, AssessDBKey.DeclareBuildingConstructionPermit, data.id);
                            civilEngineering.fileUpload(civilEngineeringConfig.declareBuildingConstructionPermit.fileId, AssessDBKey.DeclareBuildingConstructionPermit, data.id);
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        } else {
            $("#" + civilEngineeringConfig.declareBuildingConstructionPermit.frm).initForm({pid: id});
            civilEngineering.showFile(civilEngineeringConfig.declareBuildingConstructionPermit.fileId, AssessDBKey.DeclareBuildingConstructionPermit, 0);
            civilEngineering.fileUpload(civilEngineeringConfig.declareBuildingConstructionPermit.fileId, AssessDBKey.DeclareBuildingConstructionPermit, 0);
        }
        $('#' + civilEngineeringConfig.declareBuildingConstructionPermit.box).modal("show");
    };


    /**
     * @author:  zch
     * 描述:建筑工程施工许可证 更新
     * @date:2018-09-28
     **/
    civilEngineering.declareBuildingConstructionPermitSaveAndUpdate = function () {
        if (!$("#" + civilEngineeringConfig.declareBuildingConstructionPermit.frm).valid()) {
            return false;
        }
        var data = formParams(civilEngineeringConfig.declareBuildingConstructionPermit.frm);
        <%--data.planDetailsId = '${empty projectPlanDetails.id?0:projectPlanDetails.id}';--%>
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareBuildingConstructionPermit/saveAndUpdateDeclareBuildingConstructionPermit",
            data: data,
            success: function (result) {
                if (result.ret) {
                    $('#' + civilEngineeringConfig.declareBuildingConstructionPermit.box).modal("hide");
                    var item = result.data;
                    if (civilEngineering.isEmpty(item)) {//把新增的子类绑定到父类上区 (依据是每次新增都会把保存的id返回回来)
                        var pid = data.pid;
                        var fData = $("#" + civilEngineeringConfig.table).bootstrapTable('getRowByUniqueId', pid);
                        fData.buildingConstructionPermitId = item;
                        civilEngineering.handleFather(fData);
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

    civilEngineering.loadList = function () {
        var cols = [];
        cols.push({field: 'provinceName', title: '省'});
        cols.push({field: 'cityName', title: '市'});
        cols.push({field: 'districtName', title: '县'});
        cols.push({field: 'bookNetValue', title: '帐面净值'});
        cols.push({field: 'bookValue', title: '帐面价值'});
        cols.push({field: 'declarer', title: '申报人'});
        cols.push({field: 'beLocated', title: '坐落'});
        cols.push({field: 'fileViewName', title: '附件'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="dropdown">';
                str += "<button class='btn dropdown-toggle' data-toggle='dropdown' id='dropdownMenu1'>"+"操作" +"<span class='caret'>"+"</span>"+"</button>";
                str += "<ul class='dropdown-menu' role='menu' aria-labelledby='dropdownMenu1'>" ;
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.deleteData(" +row.id+")'"+">"+"删除"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.editData(" +row.id+")'"+">"+"编辑"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareRealtyLandCertView(" +row.id+")'"+">"+"土地证"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareRealtyRealEstateCertView(" +row.id+")'"+">"+"不动产"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareBuildingPermitView(" +row.id+")'"+">"+"建设工程规划许可证"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareLandUsePermitView(" +row.id+")'"+">"+"建设用地规划许可证"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declareBuildingConstructionPermitView(" +row.id+")'"+">"+"建筑工程施工许可证"+ "</a>" + "</li>";
                str += "<li role='presentation'>"+ "<a role='menuitem' tabindex='-1' class='btn btn-default' onclick='civilEngineering.declarePreSalePermitView(" +row.id+")'"+">"+"商品房预售许可证"+ "</a>" + "</li>";
                str += "</ul>" ;
                str += "</div>";
                return str;
            }
        });
        $("#" + civilEngineeringConfig.table).bootstrapTable('destroy');
        TableInit(civilEngineeringConfig.table, "${pageContext.request.contextPath}/declareBuildEngineering/getDeclareBuildEngineeringList", cols, {
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
    civilEngineering.inputFile = function () {
        $.ajaxFileUpload({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareBuildEngineering/importData",
            data: {
                planDetailsId: ${empty projectPlanDetails.id?0:projectPlanDetails.id}
            },//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: civilEngineeringConfig.excelUpload,//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                if (result.ret) {
                    civilEngineering.loadList();
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
        civilEngineering.loadList();
    });

</script>

<!-- 建设工程规划许可证 -->
<div id="declareBuildingPermitBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
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
            <form id="declareBuildingPermitFrm" class="form-horizontal">
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
                                                   placeholder="建设位置" name="location" class="form-control"
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
                                            <input id="declareBuildingPermitFileId"
                                                   name="declareBuildingPermitFileId"
                                                   required="required" placeholder="附件" class="form-control"
                                                   type="file">
                                            <div id="_declareBuildingPermitFileId"></div>
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
                        onclick="civilEngineering.declareBuildingPermitSaveAndUpdate()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 建设用地规划许可证 -->
<div id="declareLandUsePermitBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
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
            <form id="declareLandUsePermitFrm" class="form-horizontal">
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
                                                   placeholder="用地面积" data-rule-maxlength="100" data-rule-number='true'
                                                   name="area" class="form-control"
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
                                            <input id="declareLandUsePermitFileId"
                                                   name="declareLandUsePermitFileId"
                                                   required="required" placeholder="附件" class="form-control"
                                                   type="file">
                                            <div id="_declareLandUsePermitFileId"></div>
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
                        onclick="civilEngineering.declareLandUsePermitSaveAndUpdate()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 商品房预售许可证 -->
<div id="declarePreSalePermitBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
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
            <form id="declarePreSalePermitFrm" class="form-horizontal">
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
                                                   placeholder="预售面积(数字)" data-rule-maxlength="100"
                                                   data-rule-number='true' name="preSaleArea" class="form-control"
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
                                                   placeholder="预售款监管信息" name="preSaleSupervisionInformation"
                                                   class="form-control"
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
                                            <input id="declarePreSalePermitFileId"
                                                   name="declareBuildingConstructionPermitFileId"
                                                   required="required" placeholder="附件" class="form-control"
                                                   type="file">
                                            <div id="_declarePreSalePermitFileId"></div>
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
                        onclick="civilEngineering.declarePreSalePermitSaveAndUpdate()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 建筑工程施工许可证 -->
<div id="declareBuildingConstructionPermitBox" class="modal fade bs-example-modal-lg" data-backdrop="static"
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
            <form id="declareBuildingConstructionPermitFrm" class="form-horizontal">
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
                                            <input id="declareBuildingConstructionPermitFileId"
                                                   name="declareBuildingConstructionPermitFileId"
                                                   required="required" placeholder="附件" class="form-control"
                                                   type="file">
                                            <div id="_declareBuildingConstructionPermitFileId"></div>
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
                        onclick="civilEngineering.declareBuildingConstructionPermitSaveAndUpdate()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 在建工程（土建） -->
<div id="civilEngineeringBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土建</h3>
            </div>
            <form id="civilEngineeringFrm" class="form-horizontal">
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
                                            <select name="province" id="civilEngineeringFrmprovince"
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
                                            <select id="civilEngineeringFrmcity" name="city"
                                                    class="form-control search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>

                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">县<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select id="civilEngineeringFrmdistrict" name="district"
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
                                        <label class="col-sm-1 control-label">结构<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="结构" name="structure" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建筑面积<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建筑面积(数字)" name="buildArea" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
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
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">预期完成日期<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input placeholder="预期完成日期"
                                                   name="expectedCompletionDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate roomTime" required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">形象进度<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="形象进度" name="speedProgress" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">付款比例<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="付款比例" name="paymentRatio" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">账面价值<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="账面价值" name="bookValue" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">帐面净值<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="帐面净值" name="bookNetValue" class="form-control"
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
                                </div>

                                <div class="form-group">
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
                                        <label class="col-sm-1 control-label">备注<span
                                                class="symbol required"></span></label>
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
                                            <input id="civilEngineeringFileId" name="civilEngineeringFileId"
                                                   required="required" placeholder="附件" class="form-control"
                                                   type="file">
                                            <div id="_civilEngineeringFileId"></div>
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
                        onclick="civilEngineering.saveAndUpdateData();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 土地证 -->
<div id="declareRealtyLandCertBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土地证</h3>
            </div>
            <form id="declareRealtyLandCertFrm" class="form-horizontal">
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
                                            <select name="province" id="declareRealtyLandCertFrmprovince"
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
                                            <select id="declareRealtyLandCertFrmcity" name="city"
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
                                            <select id="declareRealtyLandCertFrmdistrict" name="district"
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
                                            <input type="text" name="location" required="required" class="form-control"
                                                   placeholder="所在地">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            土地使用权人
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" name="ownership" required="required" class="form-control"
                                                   placeholder="土地使用权人">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            土地权证号
                                        </label>
                                        <div class="col-sm-11">
                                            <input type="text" name="landCertName" readonly="readonly"
                                                   class="form-control" placeholder="土地权证号">
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
                                            <input type="text" required="required" data-rule-maxlength="100"
                                                   data-rule-number='true' name="year" class="form-control"
                                                   placeholder="年份(数字如:2018)">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            编号
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" required="required" name="number" class="form-control"
                                                   placeholder="编号">
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
                                            <select required="required" name="useRightType"
                                                    class="form-control search-select select2 useRightType">
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
                                            <input id="declareRealtyLandCertFileId" name="declareRealtyLandCertFileId"
                                                   required="required" placeholder="上传附件" class="form-control"
                                                   type="file">
                                            <div id="_declareRealtyLandCertFileId"></div>
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
                            onclick="civilEngineering.declareRealtyLandCertSaveAndUpdate();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 不动产 -->
<div id="declareRealtyRealEstateCertBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">不动产</h3>
            </div>
            <form id="declareRealtyRealEstateCertFrm" class="form-horizontal">
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
                                            <select name="province" id="declareRealtyRealEstateCertFrmprovince"
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
                                            <select id="declareRealtyRealEstateCertFrmcity" name="city"
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
                                            <select id="declareRealtyRealEstateCertFrmdistrict" name="district"
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
                                            <input type="text"
                                                   placeholder="共有情况" name="publicSituation" class="form-control"
                                                   required="required">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">建筑面积<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="建筑面积(数字)" name="floorArea" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'
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
                                                   required="required" data-rule-maxlength="100"
                                                   data-rule-number='true'>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">证载面积<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="证载面积(数字)" name="evidenceArea" class="form-control"
                                                   required="required" data-rule-maxlength="100"
                                                   data-rule-number='true'>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">套内面积<span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="套内面积(数字)" name="innerArea" class="form-control"
                                                   required="required" data-rule-maxlength="100"
                                                   data-rule-number='true'>
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
                                                   required="required" data-rule-maxlength="100"
                                                   data-rule-number='true'>
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
                                            <select required="required" name="useRightType"
                                                    class="form-control search-select select2 useRightType">
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
                                            <input id="declareRealtyRealEstateCertFileId"
                                                   name="declareRealtyRealEstateCertFileId"
                                                   required="required" placeholder="附件" class="form-control"
                                                   type="file">
                                            <div id="_declareRealtyRealEstateCertFileId"></div>
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
                    <label class="btn btn-primary"
                           onclick="civilEngineering.declareRealtyRealEstateCertSaveAndUpdate()">
                        保存
                    </label>
                </div>
            </form>
        </div>
    </div>
</div>