<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/9/19
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_content">
        <div class="x_title">
            <h3>
                土地证
                &nbsp;&nbsp;&nbsp;&nbsp;
                <small>
                    <div class="btn-group">
                        <button type="button" class="btn btn-success" onclick="declareRealtyLandCert.showAddModel()"
                                data-toggle="modal"> 新增
                        </button>
                    </div>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">导入土地证数据
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a class="btn"
                                   onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftLandOwnershipCertificate)">下载模板</a>
                            </li>
                            <li><a class="btn btn-default" onclick="$('#ajaxFileUploadLand').val('').trigger('click')">导入</a>
                            </li>
                        </ul>
                    </div>

                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">导入房产证数据
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a class="btn"
                                   onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftHouseOwnershipCertificate)">下载模板</a>
                            </li>
                            <li><a class="btn btn-default"
                                   onclick="$('#ajaxFileUploadLandHouse').val('').trigger('click')">导入</a></li>
                        </ul>
                    </div>
                </small>
            </h3>
            <div class="clearfix"></div>
        </div>
        <form class="form-horizontal">
            <div class="form-group">
                <div class="x-valid">
                    <table class="table table-bordered" id="tableDeclareRealtyLandCert">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>
<input type="file" id="ajaxFileUploadLand" name="file" style="display: none;"
       onchange="declareRealtyLandCert.inputFile();">
<input type="file" id="ajaxFileUploadLandHouse" name="file" style="display: none;"
       onchange="declareRealtyLandCert.inputFileHouse();">
<input type="file" id="declareRealtyLandCertNewFileId" name="declareRealtyLandCertNewFileId" style="display: none;"
       onchange="declareRealtyLandCert.landEnclosureFun();">
<input type="file" id="declareRealtyLandCert_declareRealtyLandCert_HouseCert_newFileId" name="declareRealtyLandCert_declareRealtyLandCert_HouseCert_newFileId"
       onchange="declareRealtyLandCert.houseEnclosureFun();">
<script>
    var declareRealtyLandCertConfig = {
        frm: "frmDeclareRealtyLandCert",
        name: "土地证",
        table: "tableDeclareRealtyLandCert",
        box: "boxDeclareRealtyLandCert",
        fileId: "declareRealtyLandCertFileId",
        newFileId: "declareRealtyLandCertNewFileId",
        houseFileId: "declareRealtyLandCert_declareRealtyLandCert_HouseCert_FileId",
        newHouseFileId: "declareRealtyLandCert_declareRealtyLandCert_HouseCert_newFileId",
        HouseCert: {
            frm: "declareRealtyLandCert_HouseCert_frm",
            box: "declareRealtyLandCert_HouseCert_box"
        }
    };

    var declareRealtyLandCert = new Object();

    declareRealtyLandCert.declareRealtyLandCertFlag = true;
    declareRealtyLandCert.startPath = null;

    declareRealtyLandCert.fileUpload = function (target, tableName, id) {
        FileUtils.uploadFiles({
            target: target,
            disabledTarget: "btn_submit",
            formData: {
                tableName: tableName,
                tableId: id,
                projectId: "${projectPlanDetails.projectId}",
                creater: "${currUserAccount}"
            },
            deleteFlag: true
        });
    };

    declareRealtyLandCert.fileUpload2 = function (target, tableName, id) {
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
                declareRealtyLandCert.showFile(target, tableName, id);
                if (target == declareRealtyLandCertConfig.fileId) {
                    if (declareRealtyLandCert.isEmpty(result)) {
                        AssessCommon.downloadFtpFileToLocal(result, function (data) {
                            declareRealtyLandCert.startPath = data;
                        });
                    }
                }
                declareRealtyLandCert.loadList();
            },
            deleteFlag: true
        });
    };

    declareRealtyLandCert.showFile = function (target, tableName, id) {
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

    declareRealtyLandCert.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    /**
     * @author:  zch
     * 描述:数据拼接
     * @date:2018-09-21
     **/
    declareRealtyLandCert.role = {
        CertName: {
            init: function () {
                $("#" + declareRealtyLandCertConfig.frm + " input[name='location']").blur(function () {
                    if (declareRealtyLandCert.isEmpty($("#" + declareRealtyLandCertConfig.frm + " input[name='location']").val())) {
                        declareRealtyLandCert.role.CertName.write();
                    }
                });
                $("#" + declareRealtyLandCertConfig.frm + " input[name='year']").blur(function () {
                    if (declareRealtyLandCert.isEmpty($("#" + declareRealtyLandCertConfig.frm + " input[name='year']").val())) {
                        declareRealtyLandCert.role.CertName.write();
                    }
                });
                $("#" + declareRealtyLandCertConfig.frm + " input[name='number']").blur(function () {
                    if (declareRealtyLandCert.isEmpty($("#" + declareRealtyLandCertConfig.frm + " input[name='number']").val())) {
                        declareRealtyLandCert.role.CertName.write();
                    }
                });
                $("#" + declareRealtyLandCertConfig.frm + " .type").change(function () {
                    /**
                     * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
                     **/
                    var id = $("#" + declareRealtyLandCertConfig.frm + " .type").eq(1).val();
                    if (declareRealtyLandCert.isEmpty(id)) {
                        declareRealtyLandCert.role.CertName.write();
                    }
                });
            },
            write: function () {
                var temp = "";
                var id = $("#" + declareRealtyLandCertConfig.frm + " .type").eq(1).val();
                var location = $("#" + declareRealtyLandCertConfig.frm + " input[name='location']").val();
                var year = $("#" + declareRealtyLandCertConfig.frm + " input[name='year']").val();
                var number = $("#" + declareRealtyLandCertConfig.frm + " input[name='number']").val();
                if (declareRealtyLandCert.isEmpty(id)) {
                    AssessCommon.getProjectClassifyInfo(id, function (data) {
                        if (declareRealtyLandCert.isEmpty(data)) {
                            var temp = location + data.name + year + "第" + number + "号";
                            $("#" + declareRealtyLandCertConfig.frm + " input[name='landCertName']").val(temp);
                        }
                    });
                } else {
                    var temp = location + year + "第" + number + "号";
                    $("#" + declareRealtyLandCertConfig.frm + " input[name='landCertName']").val(temp);
                }
            }
        },
        beLocated: {
            init: function () {
                $("#" + declareRealtyLandCertConfig.frm + " input[name='unit']").blur(function () {
                    if (declareRealtyLandCert.isEmpty($("#" + declareRealtyLandCertConfig.frm + " input[name='unit']").val())) {
                        declareRealtyLandCert.role.beLocated.write();
                    }
                });
                $("#" + declareRealtyLandCertConfig.frm + " input[name='floor']").blur(function () {
                    if (declareRealtyLandCert.isEmpty($("#" + declareRealtyLandCertConfig.frm + " input[name='floor']").val())) {
                        declareRealtyLandCert.role.beLocated.write();
                    }
                });
                $("#" + declareRealtyLandCertConfig.frm + " input[name='roomNumber']").blur(function () {
                    if (declareRealtyLandCert.isEmpty($("#" + declareRealtyLandCertConfig.frm + " input[name='roomNumber']").val())) {
                        declareRealtyLandCert.role.beLocated.write();
                    }
                });
                $("#" + declareRealtyLandCertConfig.frm + " input[name='streetNumber']").blur(function () {
                    if (declareRealtyLandCert.isEmpty($("#" + declareRealtyLandCertConfig.frm + " input[name='streetNumber']").val())) {
                        declareRealtyLandCert.role.beLocated.write();
                    }
                });
                $("#" + declareRealtyLandCertConfig.frm + " input[name='attachedNumber']").blur(function () {
                    if (declareRealtyLandCert.isEmpty($("#" + declareRealtyLandCertConfig.frm + " input[name='attachedNumber']").val())) {
                        declareRealtyLandCert.role.beLocated.write();
                    }
                });
                $("#" + declareRealtyLandCertConfig.frm + " input[name='buildingNumber']").blur(function () {
                    if (declareRealtyLandCert.isEmpty($("#" + declareRealtyLandCertConfig.frm + " input[name='buildingNumber']").val())) {
                        declareRealtyLandCert.role.beLocated.write();
                    }
                });
                $("#" + declareRealtyLandCertConfig.frm + " .district").change(function () {
                    /**
                     * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
                     **/
                    var id = $("#" + declareRealtyLandCertConfig.frm + " .district").eq(1).val();
                    if (declareRealtyLandCert.isEmpty(id)) {
                        declareRealtyLandCert.role.beLocated.write();
                    }
                });
            },
            write: function () {
                var temp = null;
                var district = $("#" + declareRealtyLandCertConfig.frm + " .district").eq(1).val();
                var unit = $("#" + declareRealtyLandCertConfig.frm + " input[name='unit']").val();
                var floor = $("#" + declareRealtyLandCertConfig.frm + " input[name='floor']").val();
                var roomNumber = $("#" + declareRealtyLandCertConfig.frm + " input[name='roomNumber']").val();
                var streetNumber = $("#" + declareRealtyLandCertConfig.frm + " input[name='streetNumber']").val();
                var attachedNumber = $("#" + declareRealtyLandCertConfig.frm + " input[name='attachedNumber']").val();
                var buildingNumber = $("#" + declareRealtyLandCertConfig.frm + " input[name='buildingNumber']").val();
                if (!declareRealtyLandCert.isEmpty(unit)) {
                    unit = "";
                } else {
                    unit = unit + "单元";
                }
                if (!declareRealtyLandCert.isEmpty(floor)) {
                    floor = "";
                } else {
                    floor = floor + "楼";
                }
                if (!declareRealtyLandCert.isEmpty(roomNumber)) {
                    roomNumber = "";
                } else {
                    roomNumber = roomNumber + "号";
                }
                if (!declareRealtyLandCert.isEmpty(streetNumber)) {
                    streetNumber = "";
                }
                if (!declareRealtyLandCert.isEmpty(attachedNumber)) {
                    attachedNumber = "";
                } else {
                    attachedNumber = "附" + attachedNumber;
                }
                if (!declareRealtyLandCert.isEmpty(buildingNumber)) {
                    buildingNumber = "";
                } else {
                    buildingNumber = buildingNumber + "栋";
                }
                if (declareRealtyLandCert.isEmpty(district)) {
                    AssessCommon.getAreaById(district, function (data) {
                        if (!declareRealtyLandCert.isEmpty(data)) {
                            district = "";
                        } else {
                            district = data.name;
                        }
                        temp = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                        $("#" + declareRealtyLandCertConfig.frm + " input[name='beLocated']").val(temp);
                    });
                } else {
                    district = "";
                    temp = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                    $("#" + declareRealtyLandCertConfig.frm + " input[name='beLocated']").val(temp);
                }
            }
        }
    };

    declareRealtyLandCert.objectWriteSelectData = function (frm, data, name) {
        if (declareRealtyLandCert.isEmpty(data)) {
            $("#" + frm + " ." + name).val(data).trigger("change");
        } else {
            $("#" + frm + " ." + name).val(null).trigger("change");
        }
    };

    declareRealtyLandCert.init = function () {
        AssessCommon.initAreaInfo({
            provinceTarget: $("#" + declareRealtyLandCertConfig.frm + "province"),
            cityTarget: $("#" + declareRealtyLandCertConfig.frm + "city"),
            districtTarget: $("#" + declareRealtyLandCertConfig.frm + "district"),
            provinceValue: '',
            cityValue: '',
            districtValue: ''
        });
        AssessCommon.getProjectClassifyListByFieldName(AssessProjectClassifyKey.singleLandPropertyCertificateTypeCategory, function (html, data) {
            $("#" + declareRealtyLandCertConfig.frm).find('select.type').empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, "", function (html, data) {
            $("#" + declareRealtyLandCertConfig.frm).find('select.purpose').empty().html(html).trigger('change');
        });
        declareRealtyLandCert.role.CertName.init();
        declareRealtyLandCert.role.beLocated.init();
    };

    declareRealtyLandCert.showAddModel = function () {
        $("#" + declareRealtyLandCertConfig.frm).clearAll();
        if (declareRealtyLandCert.declareRealtyLandCertFlag) {
            declareRealtyLandCert.init();
            declareRealtyLandCert.declareRealtyLandCertFlag = false;
        }
        $("#" + declareRealtyLandCertConfig.frm).validate();
        $('#' + declareRealtyLandCertConfig.box).modal("show");
        declareRealtyLandCert.fileUpload2(declareRealtyLandCertConfig.fileId, AssessDBKey.DeclareRealtyLandCert, 0);
    };

    declareRealtyLandCert.deleteData = function (id) {
        Alert("是否删除", 2, null,
            function () {
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/declareRealtyLandCert/deleteDeclareRealtyLandCertById",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            declareRealtyLandCert.loadList();
                            toastr.success('成功!');
                        } else {
                            Alert("保存失败:" + result.errmsg);
                        }
                    },
                    error: function (e) {
                        Alert("调用服务端方法失败，失败原因:" + e);
                    }
                });
            }
        );
    };

    declareRealtyLandCert.editData = function (id) {
        $("#" + declareRealtyLandCertConfig.frm).clearAll();
        if (declareRealtyLandCert.declareRealtyLandCertFlag) {
            declareRealtyLandCert.init();
            declareRealtyLandCert.declareRealtyLandCertFlag = false;
        }
        declareRealtyLandCert.showFile(declareRealtyLandCertConfig.fileId, AssessDBKey.DeclareRealtyLandCert, id);
        declareRealtyLandCert.fileUpload2(declareRealtyLandCertConfig.fileId, AssessDBKey.DeclareRealtyLandCert, id);
        $.ajax({
            url: "${pageContext.request.contextPath}/declareRealtyLandCert/getDeclareRealtyLandCertById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    if (declareRealtyLandCert.isEmpty(data)) {
                        $("#" + declareRealtyLandCertConfig.frm).initForm(result.data);
                        $("#" + declareRealtyLandCertConfig.frm + " input[name='terminationDate']").val(formatDate(data.terminationDate));
                        $("#" + declareRealtyLandCertConfig.frm + " input[name='registrationDate']").val(formatDate(data.registrationDate));
                        declareRealtyLandCert.objectWriteSelectData(declareRealtyLandCertConfig.frm, data.purpose, "purpose");
                        declareRealtyLandCert.objectWriteSelectData(declareRealtyLandCertConfig.frm, data.type, "type");
                        declareRealtyLandCert.objectWriteSelectData(declareRealtyLandCertConfig.frm, data.useRightType, "useRightType");
                        AssessCommon.initAreaInfo({
                            provinceTarget: $("#" + declareRealtyLandCertConfig.frm + "province"),
                            cityTarget: $("#" + declareRealtyLandCertConfig.frm + "city"),
                            districtTarget: $("#" + declareRealtyLandCertConfig.frm + "district"),
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
        })
        $("#" + declareRealtyLandCertConfig.frm).validate();
        $('#' + declareRealtyLandCertConfig.box).modal("show");
    }

    declareRealtyLandCert.loadList = function () {
        var cols = [];
        cols.push({field: 'useRightType', title: '使用权类型'});
        cols.push({field: 'apportionmentArea', title: '分摊面积'});
        cols.push({field: 'useRightArea', title: '使用权面积'});
        cols.push({field: 'landCertName', title: '土地权证号'});
        cols.push({field: 'beLocated', title: '土地坐落'});
        cols.push({field: 'fileViewName', title: '附件'});
        cols.push({
            field: 'id', title: '是否关联房产证', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                if (declareRealtyLandCert.isEmpty(row.pid)) {
                    str += "已经关联";
                } else {
                    str += "未关联";
                }
                str += '</div>';
                return str;
            }
        });
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑" onclick="declareRealtyLandCert.editData(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="declareRealtyLandCert.deleteData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                str += "<a class='btn btn-xs btn-success tooltips' data-placement='top' data-original-title='土地证附件' onclick='declareRealtyLandCert.landEnclosure(" + row.id + ")'" + ">" + "<i class='fa'>" + "土地证附件" + "</a>";
                str += "<a class='btn btn-xs btn-success tooltips' data-placement='top' data-original-title='房产证附件' onclick='declareRealtyLandCert.houseEnclosure(" + row.id + ")'" + ">" + "<i class='fa'>" + "房产证附件" + "</a>";
                str += "<a class='btn btn-xs btn-success tooltips' data-placement='top' data-original-title='房产证关联' onclick='declareRealtyLandCert.houseCard(" + row.id + ")'" + ">" + "<i class='fa'>" + "房产证关联" + "</a>";
                str += '</div>';
                return str;
            }
        });
        $("#" + declareRealtyLandCertConfig.table).bootstrapTable('destroy');
        TableInit(declareRealtyLandCertConfig.table, "${pageContext.request.contextPath}/declareRealtyLandCert/getDeclareRealtyLandCertList", cols, {
            planDetailsId: '${empty projectPlanDetails.id?0:projectPlanDetails.id}'
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

    declareRealtyLandCert.saveAndUpdateData = function () {
        if (!$("#" + declareRealtyLandCertConfig.frm).valid()) {
            return false;
        }
        var data = formParams(declareRealtyLandCertConfig.frm);
        if (!declareRealtyLandCert.isEmpty(data.id)) {
            data.planDetailsId = '${empty projectPlanDetails.id?0:projectPlanDetails.id}';
            data.pid = "0";
            data.declareType = declareFunObj.getDeclareType("土地证");
        }
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareRealtyLandCert/saveAndUpdateDeclareRealtyLandCert",
            data: data,
            success: function (result) {
                if (result.ret) {
                    declareRealtyLandCert.loadList();
                    toastr.success('成功!');
                    $('#' + declareRealtyLandCertConfig.box).modal("hide");
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
     * 描述:土地证附件 页面
     * @date:2018-09-21
     **/
    declareRealtyLandCert.landEnclosure = function (id) {
        $("#"+declareRealtyLandCertConfig.newFileId).attr("data-id",id);
        $("#"+declareRealtyLandCertConfig.newFileId).trigger('click');
    };
    declareRealtyLandCert.landEnclosureFun = function () {
        var id = $("#"+declareRealtyLandCertConfig.newFileId).attr("data-id");
        $.ajaxFileUpload({
            type: "POST",
            url: "${pageContext.request.contextPath}/public/importAjaxFile",
            data: {
                planDetailsId: ${empty projectPlanDetails.id?0:projectPlanDetails.id},
                tableName:AssessDBKey.DeclareRealtyLandCert,
                tableId:id,
                fieldsName:declareRealtyLandCertConfig.fileId
            },//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: declareRealtyLandCertConfig.newFileId,//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                if (result.ret) {
                    declareRealtyLandCert.loadList();
                }
            },
            error: function (result, status, e) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    //土地证识别
    declareRealtyLandCert.distinguish = function () {
        var startPath = declareRealtyLandCert.startPath;
        if (!declareRealtyLandCert.isEmpty(startPath)) {
            toastr.success('稍后再试!');
            return false;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/declareRealtyLandCert/parseRealtyLandCertOcr",
            type: "POST",
            dataType: "json",
            data: {startPath: startPath},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    $("#" + declareRealtyLandCertConfig.frm).initForm(data);
                    declareRealtyLandCert.startPath = null;
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    };

    /**
     * @author:  zch
     * 描述:房产证附件 服务端
     * @date:2018-09-21
     **/
    declareRealtyLandCert.houseEnclosureFun = function () {
        var id = $("#"+declareRealtyLandCertConfig.newHouseFileId).attr("data-id");
        $.ajaxFileUpload({
            type: "POST",
            url: "${pageContext.request.contextPath}/public/importAjaxFile",
            data: {
                planDetailsId: ${empty projectPlanDetails.id?0:projectPlanDetails.id},
                tableName:AssessDBKey.DeclareRealtyHouseCert,
                tableId:id,
                fieldsName:declareRealtyLandCertConfig.houseFileId
            },//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: declareRealtyLandCertConfig.newHouseFileId,//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                if (result.ret) {
                    declareRealtyLandCert.loadList();
                }
            },
            error: function (result, status, e) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };
    /**
     * @author:  zch
     * 描述:房产证附件 页面
     * @date:2018-09-21
     **/
    declareRealtyLandCert.houseEnclosure = function (id) {
        var data = $("#" + declareRealtyLandCertConfig.table).bootstrapTable('getRowByUniqueId', id);
        if (declareRealtyLandCert.isEmpty(data.pid)) {
            $.ajax({
                url: "${pageContext.request.contextPath}/declareRealtyHouseCert/getDeclareRealtyHouseCertById",
                type: "get",
                dataType: "json",
                data: {id: data.pid, planDetailsId: '${empty projectPlanDetails.id?0:projectPlanDetails.id}'},
                success: function (result) {
                    if (result.ret) {
                        var item = result.data;
                        if (declareRealtyLandCert.isEmpty(item)) {
                            $("#"+declareRealtyLandCertConfig.newHouseFileId).attr("data-id",item.id);
                            $("#"+declareRealtyLandCertConfig.newHouseFileId).trigger('click');
                        } else {
                            toastr.success('关联数据已经被删除了!');
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        } else {
            toastr.success('请关联房产证数据!');
        }
    };

    /**
     * @author:  zch
     * 描述:房产证数据
     * @date:2018-09-21
     **/
    declareRealtyLandCert.houseCard = function (id) {
        $("#" + declareRealtyLandCertConfig.HouseCert.frm).clearAll();
        $('#' + declareRealtyLandCertConfig.HouseCert.box).modal("show");
        var item = $("#" + declareRealtyLandCertConfig.table).bootstrapTable('getRowByUniqueId', id);
        if (declareRealtyLandCert.isEmpty(item.pid)) {
            $.ajax({
                url: "${pageContext.request.contextPath}/declareRealtyHouseCert/getDeclareRealtyHouseCertById",
                type: "get",
                dataType: "json",
                data: {id: item.pid, planDetailsId: '${empty projectPlanDetails.id?0:projectPlanDetails.id}'},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (declareRealtyLandCert.isEmpty(data)) {
                            $("#" + declareRealtyLandCertConfig.HouseCert.frm).initForm(data);
                            $("#" + declareRealtyLandCertConfig.HouseCert.frm + " input[name='registrationTime']").val(formatDate(data.registrationTime));
                            $("#" + declareRealtyLandCertConfig.HouseCert.frm + " input[name='useEndDate']").val(formatDate(data.useEndDate));
                            $("#" + declareRealtyLandCertConfig.HouseCert.frm + " input[name='useStartDate']").val(formatDate(data.useStartDate));
                            $("#" + declareRealtyLandCertConfig.HouseCert.frm + " input[name='registrationDate']").val(formatDate(data.registrationDate));
                            declareRealtyLandCert.objectWriteSelectData(declareRealtyLandCertConfig.HouseCert.frm, data.type, "type");
                            declareRealtyLandCert.showFile(declareRealtyLandCertConfig.houseFileId, AssessDBKey.DeclareRealtyHouseCert, data.id);
                            declareRealtyLandCert.fileUpload2(declareRealtyLandCertConfig.houseFileId, AssessDBKey.DeclareRealtyHouseCert, data.id);
                        } else {
                            toastr.success('关联数据已经被删除了!');
                            toastr.success('请重新填写');
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        } else {
            $.ajax({
                url: "${pageContext.request.contextPath}/declareRealtyLandCert/getDeclareRealtyLandCertById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (declareRealtyLandCert.isEmpty(data)) {
                            AssessCommon.initAreaInfo({
                                provinceTarget: $("#" + declareRealtyLandCertConfig.HouseCert.frm + "province"),
                                cityTarget: $("#" + declareRealtyLandCertConfig.HouseCert.frm + "city"),
                                districtTarget: $("#" + declareRealtyLandCertConfig.HouseCert.frm + "district"),
                                provinceValue: data.province,
                                cityValue: data.city,
                                districtValue: data.district
                            });
                            $("#" + declareRealtyLandCertConfig.HouseCert.frm).initForm(
                                {
                                    pid: id,
                                    beLocated:result.data.beLocated,
                                    streetNumber:result.data.streetNumber,
                                    attachedNumber:result.data.attachedNumber,
                                    buildingNumber:result.data.buildingNumber,
                                    unit:result.data.unit,
                                    roomNumber:result.data.roomNumber,
                                    floor:result.data.floor
                                }
                                );
                            declareRealtyLandCert.fileUpload2(declareRealtyLandCertConfig.houseFileId, AssessDBKey.DeclareRealtyHouseCert, 0);
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        }
    };

    declareRealtyLandCert.houseCardSaveAndUpdate = function () {
        if (!$("#" + declareRealtyLandCertConfig.HouseCert.frm).valid()) {
            return false;
        }
        var data = formParams(declareRealtyLandCertConfig.HouseCert.frm);
        <%--data.planDetailsId = '${empty projectPlanDetails.id?0:projectPlanDetails.id}';--%>
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareRealtyHouseCert/saveAndUpdateDeclareRealtyHouseCert",
            data: data,
            success: function (result) {
                if (result.ret) {
                    toastr.success('成功!');
                    $('#' + declareRealtyLandCertConfig.HouseCert.box).modal("hide");
                    declareRealtyLandCert.loadList();
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };


    declareRealtyLandCert.inputFileHouse = function () {
        $.ajaxFileUpload({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareRealtyLandCert/importDataHouse",
            data: {
                planDetailsId: ${empty projectPlanDetails.id?0:projectPlanDetails.id}
            },//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: 'ajaxFileUploadLandHouse',//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                if (result.ret) {
                    declareRealtyLandCert.loadList();
                    Alert(result.data);
                }
            },
            error: function (result, status, e) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    /**
     * @author:  zch
     * 描述:批量导入
     * @date:2018-09-21
     **/
    declareRealtyLandCert.inputFile = function () {
        $.ajaxFileUpload({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareRealtyLandCert/importData",
            data: {
                planDetailsId: ${empty projectPlanDetails.id?0:projectPlanDetails.id}
            },//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: 'ajaxFileUploadLand',//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                if (result.ret) {
                    declareRealtyLandCert.loadList();
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
        declareRealtyLandCert.loadList();
    });
</script>

<!-- 土地证信息 -->
<div id="boxDeclareRealtyLandCert" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土地证信息</h3>
            </div>
            <form id="frmDeclareRealtyLandCert" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            上传土地证<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <input id="declareRealtyLandCertFileId"
                                                   name="declareRealtyLandCertFileId" placeholder="上传土地证"
                                                   class="form-control"
                                                   type="file">
                                            <div id="_declareRealtyLandCertFileId"></div>
                                        </div>
                                    </div>
                                    <div class="col-sm-4">
                                        <label class="btn btn-default"
                                               onclick="declareRealtyLandCert.distinguish();">识别</label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            省<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <select name="province" id="frmDeclareRealtyLandCertprovince"
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
                                            市<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <select id="frmDeclareRealtyLandCertcity" name="city"
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
                                            <select id="frmDeclareRealtyLandCertdistrict" name="district"
                                                    class="form-control search-select select2 district">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            所在地<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" name="location" required="required" class="form-control"
                                                   placeholder="所在地">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            土地使用权人<span class="symbol required"></span>
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
                                            类型<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <select required="required" name="type"
                                                    class="form-control search-select select2 type">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            年份<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" required="required" data-rule-maxlength="100"
                                                   data-rule-number='true' name="year" class="form-control"
                                                   placeholder="年份(数字如:2018)">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            编号<span class="symbol required"></span>
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
                                            <select name="purpose"
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
                                        <label class="col-sm-1 control-label">独用面积</label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="独用面积(数字)" name="acreage" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">分摊面积</label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="分摊面积(数字)" name="apportionmentArea" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'>
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


                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="declareRealtyLandCert.saveAndUpdateData();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 房产证信息 -->
<div id="declareRealtyLandCert_HouseCert_box" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">关联房产证信息</h3>
            </div>
            <form id="declareRealtyLandCert_HouseCert_frm" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="pid">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">省
                                            <span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select name="province" id="declareRealtyLandCert_HouseCert_frmprovince"
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
                                            <select id="declareRealtyLandCert_HouseCert_frmcity" name="city"
                                                    class="form-control search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>

                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">县<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select id="declareRealtyLandCert_HouseCert_frmdistrict" name="district"
                                                    class="form-control search-select select2 district">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房屋坐落</label>
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
                                        <label class="col-sm-1 control-label">栋号</label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="栋号(数字)" name="buildingNumber" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">单元</label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="单元(数字)" name="unit" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">楼层</label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="楼层(数字)" name="floor" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">房号</label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="房号(数字)" name="roomNumber" class="form-control"
                                                   data-rule-maxlength="100" data-rule-number='true'>
                                        </div>
                                    </div>
                                </div>

                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>

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
                                        <label class="col-sm-1 control-label">类型</label>
                                        <div class="col-sm-3">
                                            <select name="type" class="form-control search-select select2 type">
                                            </select>
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

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            上传附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <input id="declareRealtyLandCert_declareRealtyLandCert_HouseCert_FileId"
                                                   name="declareRealtyLandCert_declareRealtyLandCert_HouseCert_FileId" placeholder="上传附件"
                                                   class="form-control"
                                                   type="file">
                                            <div id="_declareRealtyLandCert_declareRealtyLandCert_HouseCert_FileId"></div>
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
                        onclick="declareRealtyLandCert.houseCardSaveAndUpdate();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
