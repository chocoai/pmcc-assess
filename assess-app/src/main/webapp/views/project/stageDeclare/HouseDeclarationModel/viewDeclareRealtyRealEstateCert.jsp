<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/9/19
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_content">
        <div class="x_title">
            <h3>
                不动产证
                &nbsp;&nbsp;&nbsp;&nbsp;
                <button type="button" class="btn btn-success" onclick="declareRealtyRealEstateCert.showAddModel()"
                        data-toggle="modal"> 新增
                </button>

                &nbsp;&nbsp;&nbsp;&nbsp;

                <div class="btn-group">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">批量导入数据
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a class="btn"
                               onclick="AssessCommon.downloadFileTemplate(AssessFTKey.ftRealEstateOwnershipCertificate)">下载模板</a>
                        </li>
                        <li><a class="btn btn-default"
                               onclick="$('#ajaxFileUploadRealEstate').val('').trigger('click')">导入</a></li>
                    </ul>
                </div>
            </h3>
            <div class="clearfix"></div>
        </div>
        <form class="form-horizontal">
            <div class="form-group">
                <div class="x-valid">
                    <table class="table table-bordered" id="tableDeclareRealtyRealEstateCert">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>
<input type="file" id="ajaxFileUploadRealEstate" name="file" style="display: none;"
       onchange="declareRealtyRealEstateCert.inputFile();">

<script>
    var declareRealtyRealEstateCertConfig = {
        frm: "frmDeclareRealtyRealEstateCert",
        name: "房产证",
        table: "tableDeclareRealtyRealEstateCert",
        box: "boxDeclareRealtyRealEstateCert",
        fileId: "declareRealtyRealEstateCertFileId",
        newFileId: "declareRealtyRealEstateCertNewFileId",
        fileView: "declareRealtyRealEstateCertFileView"
    };

    var declareRealtyRealEstateCert = new Object();
    //标识符
    declareRealtyRealEstateCert.declareRealtyRealEstateCertFlag = true;
    declareRealtyRealEstateCert.startPath = null;
    declareRealtyRealEstateCert.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    declareRealtyRealEstateCert.fileUpload2 = function (target, tableName, id) {
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
                declareRealtyRealEstateCert.showFile(target, tableName, id);
                //不动产识别
                if (target == declareRealtyRealEstateCertConfig.newFileId) {
                    if (declareRealtyRealEstateCert.isEmpty(result)){
                        AssessCommon.downloadFtpFileToLocal(result,function (data) {
                            declareRealtyRealEstateCert.startPath = data;
                        });
                    }
                }
                declareRealtyRealEstateCert.loadList();
            },
            deleteFlag: true
        });
    };

    /**
     * @author:  zch
     * 描述:房产证 文件显示
     * @date:2018-09-19
     **/
    declareRealtyRealEstateCert.showFile = function (target, tableName, id) {
        FileUtils.getFileShows({
            target: target,
            formData: {
                fieldsName: target,
                tableName: tableName,
                tableId: id,
                projectId: 0
            },
            deleteFlag: true
        })
    };

    /**
     * @author:  zch
     * 描述:数据拼接
     * @date:2018-09-21
     **/
    declareRealtyRealEstateCert.role = {
        CertName: {
            init: function () {
                $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='location']").blur(function () {
                    if (declareRealtyRealEstateCert.isEmpty($("#" + declareRealtyRealEstateCertConfig.frm + " input[name='location']").val())) {
                        declareRealtyRealEstateCert.role.CertName.write();
                    }
                });
                $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='number']").blur(function () {
                    if (declareRealtyRealEstateCert.isEmpty($("#" + declareRealtyRealEstateCertConfig.frm + " input[name='number']").val())) {
                        declareRealtyRealEstateCert.role.CertName.write();
                    }
                });
                $("#" + declareRealtyRealEstateCertConfig.frm + " .type").change(function () {
                    /**
                     * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
                     **/
                    var id = $("#" + declareRealtyRealEstateCertConfig.frm + " .type").eq(1).val();
                    if (declareRealtyRealEstateCert.isEmpty(id)) {
                        declareRealtyRealEstateCert.role.CertName.write();
                    }
                });
            },
            write: function () {
                var location = $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='location']").val();
                var number = $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='number']").val();
                var id = $("#" + declareRealtyRealEstateCertConfig.frm + " .type").eq(1).val();
                if (!declareRealtyRealEstateCert.isEmpty(location)) {
                    location = "";
                }
                if (!declareRealtyRealEstateCert.isEmpty(number)) {
                    number = "";
                }
                if (!declareRealtyRealEstateCert.isEmpty(id)) {
                    id = "";
                }
                if (declareRealtyRealEstateCert.isEmpty(id)) {
                    AssessCommon.getProjectClassifyInfo(id, function (data) {
                        if (declareRealtyRealEstateCert.isEmpty(data)) {
                            var temp = location + "房权证" + data.name + "字地" + number + "号";
                            $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='certName']").val(temp);
                        }
                    });
                } else {
                    var temp = location + "房权证" + id + "字地" + number + "号";
                    $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='certName']").val(temp);
                }
            }
        },
        beLocated: {
            init: function () {
                $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='unit']").blur(function () {
                    if (declareRealtyRealEstateCert.isEmpty($("#" + declareRealtyRealEstateCertConfig.frm + " input[name='unit']").val())) {
                        declareRealtyRealEstateCert.role.beLocated.write();
                    }
                });
                $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='floor']").blur(function () {
                    if (declareRealtyRealEstateCert.isEmpty($("#" + declareRealtyRealEstateCertConfig.frm + " input[name='floor']").val())) {
                        declareRealtyRealEstateCert.role.beLocated.write();
                    }
                });
                $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='roomNumber']").blur(function () {
                    if (declareRealtyRealEstateCert.isEmpty($("#" + declareRealtyRealEstateCertConfig.frm + " input[name='roomNumber']").val())) {
                        declareRealtyRealEstateCert.role.beLocated.write();
                    }
                });
                $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='streetNumber']").blur(function () {
                    if (declareRealtyRealEstateCert.isEmpty($("#" + declareRealtyRealEstateCertConfig.frm + " input[name='streetNumber']").val())) {
                        declareRealtyRealEstateCert.role.beLocated.write();
                    }
                });
                $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='attachedNumber']").blur(function () {
                    if (declareRealtyRealEstateCert.isEmpty($("#" + declareRealtyRealEstateCertConfig.frm + " input[name='attachedNumber']").val())) {
                        declareRealtyRealEstateCert.role.beLocated.write();
                    }
                });
                $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='buildingNumber']").blur(function () {
                    if (declareRealtyRealEstateCert.isEmpty($("#" + declareRealtyRealEstateCertConfig.frm + " input[name='buildingNumber']").val())) {
                        declareRealtyRealEstateCert.role.beLocated.write();
                    }
                });
                $("#" + declareRealtyRealEstateCertConfig.frm + " .district").change(function () {
                    /**
                     * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
                     **/
                    var id = $("#" + declareRealtyRealEstateCertConfig.frm + " .district").eq(1).val();
                    if (declareRealtyRealEstateCert.isEmpty(id)) {
                        declareRealtyRealEstateCert.role.beLocated.write();
                    }
                });
            },
            write: function () {
                var temp = "";
                var district = $("#" + declareRealtyRealEstateCertConfig.frm + " .district").eq(1).val();
                var unit = $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='unit']").val();
                var floor = $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='floor']").val();
                var roomNumber = $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='roomNumber']").val();
                var streetNumber = $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='streetNumber']").val();
                var attachedNumber = $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='attachedNumber']").val();
                var buildingNumber = $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='buildingNumber']").val();
                if (!declareRealtyRealEstateCert.isEmpty(unit)) {
                    unit = "";
                } else {
                    unit = unit + "单元";
                }
                if (!declareRealtyRealEstateCert.isEmpty(floor)) {
                    floor = "";
                } else {
                    floor = floor + "楼";
                }
                if (!declareRealtyRealEstateCert.isEmpty(roomNumber)) {
                    roomNumber = "";
                } else {
                    roomNumber = roomNumber + "号";
                }
                if (!declareRealtyRealEstateCert.isEmpty(streetNumber)) {
                    streetNumber = "";
                }
                if (!declareRealtyRealEstateCert.isEmpty(attachedNumber)) {
                    attachedNumber = "";
                } else {
                    attachedNumber =   "附" + attachedNumber;
                }
                if (!declareRealtyRealEstateCert.isEmpty(buildingNumber)) {
                    buildingNumber = "";
                } else {
                    buildingNumber = buildingNumber + "栋";
                }
                if (declareRealtyRealEstateCert.isEmpty(district)) {
                    AssessCommon.getAreaById(district, function (data) {
                        if (!declareRealtyRealEstateCert.isEmpty(data)) {
                            district = "";
                        } else {
                            district = data.name;
                        }
                        temp = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                        $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='beLocated']").val(temp);
                    });
                } else {
                    district = "";
                    temp = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                    $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='beLocated']").val(temp);
                }
            }
        }
    };

    declareRealtyRealEstateCert.objectWriteSelectData = function (frm, data, name) {
        if (declareRealtyRealEstateCert.isEmpty(data)) {
            $("#" + frm + " ." + name).val(data).trigger("change");
        } else {
            $("#" + frm + " ." + name).val(null).trigger("change");
        }
    };


    declareRealtyRealEstateCert.init = function () {
        AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, "", function (html, data) {
            $("#" + declareRealtyRealEstateCertConfig.frm).find('select.purpose').empty().html(html).trigger('change');
        });
        AssessCommon.getProjectClassifyListByFieldName(AssessProjectClassifyKey.singleHousePropertyCertificateTypeCategory, function (html, data) {
            $("#" + declareRealtyRealEstateCertConfig.frm).find('select.type').empty().html(html).trigger('change');
        });
        AssessCommon.initAreaInfo({
            provinceTarget: $("#" + declareRealtyRealEstateCertConfig.frm + "province"),
            cityTarget: $("#" + declareRealtyRealEstateCertConfig.frm + "city"),
            districtTarget: $("#" + declareRealtyRealEstateCertConfig.frm + "district"),
            provinceValue: '',
            cityValue: '',
            districtValue: ''
        });
        declareRealtyRealEstateCert.role.CertName.init();
        declareRealtyRealEstateCert.role.beLocated.init();
    };

    declareRealtyRealEstateCert.showAddModel = function () {
        $("#" + declareRealtyRealEstateCertConfig.frm).clearAll();
        if (declareRealtyRealEstateCert.declareRealtyRealEstateCertFlag) {
            declareRealtyRealEstateCert.init();
            declareRealtyRealEstateCert.declareRealtyRealEstateCertFlag = false;
        }
        $("#" + declareRealtyRealEstateCertConfig.frm).validate();
        $('#' + declareRealtyRealEstateCertConfig.box).modal("show");
        declareRealtyRealEstateCert.fileUpload2(declareRealtyRealEstateCertConfig.newFileId, AssessDBKey.DeclareRealtyRealEstateCert, 0);
    };


    declareRealtyRealEstateCert.editData = function (id) {
        $("#" + declareRealtyRealEstateCertConfig.frm).clearAll();
        if (declareRealtyRealEstateCert.declareRealtyRealEstateCertFlag) {
            declareRealtyRealEstateCert.init();
            declareRealtyRealEstateCert.declareRealtyRealEstateCertFlag = false;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/declareRealtyRealEstateCert/getDeclareRealtyRealEstateCertById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    if (declareRealtyRealEstateCert.isEmpty(data)) {
                        $("#" + declareRealtyRealEstateCertConfig.frm).initForm(result.data);
                        $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='registrationTime']").val(formatDate(data.registrationTime));
                        $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='useEndDate']").val(formatDate(data.useEndDate));
                        $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='useStartDate']").val(formatDate(data.useStartDate));
                        $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='registrationDate']").val(formatDate(data.registrationDate));
                        $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='terminationDate']").val(formatDate(data.terminationDate));
                        declareRealtyRealEstateCert.objectWriteSelectData(declareRealtyRealEstateCertConfig.frm, data.type, "type");
                        declareRealtyRealEstateCert.objectWriteSelectData(declareRealtyRealEstateCertConfig.frm, data.useRightType, "useRightType");
                        declareRealtyRealEstateCert.objectWriteSelectData(declareRealtyRealEstateCertConfig.frm, data.purpose, "purpose");
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
        declareRealtyRealEstateCert.showFile(declareRealtyRealEstateCertConfig.newFileId, AssessDBKey.DeclareRealtyRealEstateCert, id);
        declareRealtyRealEstateCert.fileUpload2(declareRealtyRealEstateCertConfig.newFileId, AssessDBKey.DeclareRealtyRealEstateCert, id);
        $('#' + declareRealtyRealEstateCertConfig.box).modal("show");
        $("#" + declareRealtyRealEstateCertConfig.frm).validate();
    };

    /**
     * @author:  zch
     * 描述:附件
     * @date:2018-09-21
     **/
    declareRealtyRealEstateCert.enclosure = function (id) {
        declareRealtyRealEstateCert.showFile(declareRealtyRealEstateCertConfig.fileId, AssessDBKey.DeclareRealtyRealEstateCert, id);
        declareRealtyRealEstateCert.fileUpload2(declareRealtyRealEstateCertConfig.fileId, AssessDBKey.DeclareRealtyRealEstateCert, id);
        $('#' + declareRealtyRealEstateCertConfig.fileView).modal("show");
    };

    /**
     * @author:  zch
     * 描述:批量导入
     * @date:2018-09-21
     **/
    declareRealtyRealEstateCert.inputFile = function () {
        $.ajaxFileUpload({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareRealtyRealEstateCert/importData",
            data: {
                planDetailsId: ${empty projectPlanDetails.id?0:projectPlanDetails.id}
            },//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: 'ajaxFileUploadRealEstate',//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                if (result.ret) {
                    declareRealtyRealEstateCert.loadList();
                    Alert(result.data);
                }
            },
            error: function (result, status, e) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    declareRealtyRealEstateCert.distinguish = function () {
        var startPath = declareRealtyRealEstateCert.startPath;
        $.ajax({
            url: "${pageContext.request.contextPath}/declareRealtyRealEstateCert/parseRealtyEstateCert",
            type: "POST",
            dataType: "json",
            data: {startPath: startPath},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    $("#" + declareRealtyRealEstateCertConfig.frm).initForm(result.data);
                    $("#" + declareRealtyRealEstateCertConfig.frm + " input[name='terminationDate']").val(formatDate(data.terminationDate));
                    declareRealtyRealEstateCert.startPath = null;
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    };


    declareRealtyRealEstateCert.loadList = function () {
        var cols = [];
        cols.push({field: 'useRightType', title: '使用权类型'});
        cols.push({field: 'publicArea', title: '公摊面积'});
        cols.push({field: 'evidenceArea', title: '证载面积'});
        cols.push({field: 'certName', title: '权证号'});
        cols.push({field: 'beLocated', title: '坐落'});
        cols.push({field: 'fileViewName', title: '不动产附件'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="编辑" onclick="declareRealtyRealEstateCert.editData(' + row.id + ',\'exampleList\')"><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="declareRealtyRealEstateCert.deleteData(' + row.id + ',\'exampleList\')"><i class="fa fa-remove fa-white"></i></a>';
                str += "<a class='btn btn-xs btn-success tooltips' data-placement='top' data-original-title='不动产附件' onclick='declareRealtyRealEstateCert.enclosure(" + row.id + ")'" + ">" + "<i class='fa'>" + "不动产附件" + "</a>";
                str += '</div>';
                return str;
            }
        });
        $("#" + declareRealtyRealEstateCertConfig.table).bootstrapTable('destroy');
        TableInit(declareRealtyRealEstateCertConfig.table, "${pageContext.request.contextPath}/declareRealtyRealEstateCert/getDeclareRealtyRealEstateCertList", cols, {
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

    declareRealtyRealEstateCert.deleteData = function (id) {
        Alert("是否删除",2,null,
            function (){
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/declareRealtyRealEstateCert/deleteDeclareRealtyRealEstateCertById",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            declareRealtyRealEstateCert.loadList();
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

    declareRealtyRealEstateCert.saveAndUpdateData = function () {
        if (!$("#" + declareRealtyRealEstateCertConfig.frm).valid()) {
            return false;
        }
        var data = formParams(declareRealtyRealEstateCertConfig.frm);
        data.planDetailsId = '${empty projectPlanDetails.id?0:projectPlanDetails.id}';
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareRealtyRealEstateCert/saveAndUpdateDeclareRealtyRealEstateCert",
            data: data,
            success: function (result) {
                if (result.ret) {
                    toastr.success('成功!');
                    $('#' + declareRealtyRealEstateCertConfig.box).modal("hide");
                    declareRealtyRealEstateCert.loadList();
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };


    $(function () {
        declareRealtyRealEstateCert.loadList();
    });
</script>

<!-- 不动产附件 -->
<div id="declareRealtyRealEstateCertFileView" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">不动产附件 &nbsp;&nbsp;&nbsp;&nbsp;

                </h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel-body">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            上传不动产证
                                        </label>
                                        <div class="col-sm-11">
                                            <input id="declareRealtyRealEstateCertFileId"
                                                   name="declareRealtyRealEstateCertFileId"
                                                   required="required" placeholder="上传不动产证" class="form-control"
                                                   type="file">
                                            <div id="_declareRealtyRealEstateCertFileId"></div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 不动产 add -->
<div id="boxDeclareRealtyRealEstateCert" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">不动产证信息</h3>
            </div>
            <form id="frmDeclareRealtyRealEstateCert" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            上传不动产证<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <input id="declareRealtyRealEstateCertNewFileId"
                                                   name="declareRealtyRealEstateCertNewFileId"
                                                   required="required" placeholder="上传不动产证" class="form-control"
                                                   type="file">
                                            <div id="_declareRealtyRealEstateCertNewFileId"></div>
                                        </div>
                                    </div>
                                    <div class="col-sm-4">
                                        <label class="btn btn-default"
                                               onclick="declareRealtyRealEstateCert.distinguish();">识别</label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            省
                                        </label>
                                        <div class="col-sm-3">
                                            <select name="province" id="frmDeclareRealtyRealEstateCertprovince"
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
                                            <select id="frmDeclareRealtyRealEstateCertcity" name="city"
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
                                            <select id="frmDeclareRealtyRealEstateCertdistrict" name="district"
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
                                        <label class="col-sm-1 control-label">不动产单元号<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-11">
                                            <textarea class="form-control" name="realEstateUnitNumber" required="required">
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
                    <label class="btn btn-primary" onclick="declareRealtyRealEstateCert.saveAndUpdateData()">
                        保存
                    </label>
                </div>
            </form>
        </div>
    </div>
</div>