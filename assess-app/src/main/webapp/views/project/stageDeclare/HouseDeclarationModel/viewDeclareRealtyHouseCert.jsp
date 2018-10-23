<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/9/19
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_content">
        <div class="x_title">
            <h3>
                房产证申报 &nbsp;&nbsp;&nbsp;&nbsp;
                <small>
                    <div class="btn-group">
                        <button type="button" class="btn btn-success" onclick="declareRealtyHouseCert.showAddModel()"
                                data-toggle="modal"> 新增
                        </button>
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
                            <li><a class="btn btn-default" onclick="$('#ajaxFileUploadHouse').val('').trigger('click')">导入</a>
                            </li>
                        </ul>
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
                            <li><a class="btn btn-default"
                                   onclick="$('#ajaxFileUploadHouseLand').val('').trigger('click')">导入</a></li>
                        </ul>
                    </div>
                </small>


            </h3>
            <div class="clearfix"></div>
        </div>
        <form class="form-horizontal">
            <div class="form-group">
                <div class="x-valid">
                    <table class="table table-bordered" id="tableDeclareRealtyHouseCert">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>
<input type="file" id="ajaxFileUploadHouse" name="file" style="display: none;"
       onchange="declareRealtyHouseCert.inputFile();">

<input type="file" id="ajaxFileUploadHouseLand" name="file" style="display: none;"
       onchange="declareRealtyHouseCert.inputFileLand();">

<input type="file" id="declareRealtyHouseCertFileIdNew" name="declareRealtyHouseCertFileIdNew" style="display: none"
       onchange="declareRealtyHouseCert.enclosureServer();">

<input type="file" id="sonDeclareRealtyLandCertFileId" name="file" style="display: none" onchange="declareRealtyHouseCert.enclosureServer2()">

<script>

    /**
     * @author:  zch
     * 描述:房产证 所以配置信息
     * @date:2018-09-19
     **/
    var declareRealtyHouseCertConfig = {
        frm: "frmDeclareRealtyHouseCert",
        name: "房产证",
        table: "tableDeclareRealtyHouseCert",
        box: "boxDeclareRealtyHouseCert",
        fileId: "declareRealtyHouseCertFileId",
        fileIdNew: "declareRealtyHouseCertFileIdNew",
        fileViewNew: "declareRealtyHouseCertFileViewNew",
        landFileId: "declareRealtyHouseCert_land_FileId",
        son: {
            declareRealtyLandCert: {
                frm: "frmSonDeclareRealtyLandCert",
                box: "boxSonDeclareRealtyLandCert",
                view: "viewSonDeclareRealtyLandCertCert",
                fileId: "sonDeclareRealtyLandCertFileId",
                name: "土地",
                table: "tableSonDeclareRealtyLandCert"
            }
        }
    };

    var declareRealtyHouseCert = new Object();

    declareRealtyHouseCert.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    declareRealtyHouseCert.objectWriteSelectData = function (frm, data, name) {
        if (declareRealtyHouseCert.isEmpty(data)) {
            $("#" + frm + " ." + name).val(data).trigger("change");
        } else {
            $("#" + frm + " ." + name).val(null).trigger("change");
        }
    };

    //处理标识符的地方-------start
    declareRealtyHouseCert.declareRealtyHouseCertFlag = true;//父标识符
    declareRealtyHouseCert.sonDeclareRealtyLandCertFlag = true;//子标识符 (土地)
    declareRealtyHouseCert.sonDeclareRealtyRealEstateCert = true;//子标识符 (不动产)
    declareRealtyHouseCert.startPath = null;
    //----------------------end

    /**
     * @author:  zch
     * 描述:房产证 文件上传
     * @date:2018-09-19
     **/
    declareRealtyHouseCert.fileUpload = function (target, tableName, id) {
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
                declareRealtyHouseCert.showFile(target, tableName, id);
                //房产证识别
                if (target == declareRealtyHouseCertConfig.fileId) {
                    if (declareRealtyHouseCert.isEmpty(result)) {
                        AssessCommon.downloadFtpFileToLocal(result, function (data) {
                            declareRealtyHouseCert.startPath = data;
                        });
                    }
                }
                declareRealtyHouseCert.loadList();
            },
            deleteFlag: true
        });
    };
    declareRealtyHouseCert.fileUploadNew = function (target, tableName, id) {
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

    /**
     * @author:  zch
     * 描述:房产证 文件显示
     * @date:2018-09-19
     **/
    declareRealtyHouseCert.showFile = function (target, tableName, id) {
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
     * 描述:房产证 更新或者新增
     * @date:2018-09-19
     **/
    declareRealtyHouseCert.saveAndUpdate = function () {
        if (!$("#" + declareRealtyHouseCertConfig.frm).valid()) {
            return false;
        }
        var data = formParams(declareRealtyHouseCertConfig.frm);
        if (!declareRealtyHouseCert.isEmpty(data.id)) {
            data.planDetailsId = '${empty projectPlanDetails.id?0:projectPlanDetails.id}';
            data.pid = "0";
            data.declareType = declareFunObj.getDeclareType("房产证");
        }
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareRealtyHouseCert/saveAndUpdateDeclareRealtyHouseCert",
            data: data,
            success: function (result) {
                if (result.ret) {
                    declareRealtyHouseCert.loadList();
                    toastr.success('成功!');
                    $('#' + declareRealtyHouseCertConfig.box).modal("hide");
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    }

    /**
     * @author:  zch
     * 描述:房产证 删除
     * @date:2018-09-19
     **/
    declareRealtyHouseCert.deleteData = function (id) {

        Alert("是否删除", 2, null,
            function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/declareRealtyHouseCert/deleteDeclareRealtyHouseCertById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            declareRealtyHouseCert.loadList();
                        }
                        else {
                            Alert("保存数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            }
        );
    };

    /**
     * @author:  zch
     * 描述:房产证 编辑
     * @date:2018-09-19
     **/
    declareRealtyHouseCert.editData = function (id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/declareRealtyHouseCert/getDeclareRealtyHouseCertById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    $("#" + declareRealtyHouseCertConfig.frm).clearAll();
                    if (declareRealtyHouseCert.declareRealtyHouseCertFlag) {
                        declareRealtyHouseCert.init();
                        declareRealtyHouseCert.declareRealtyHouseCertFlag = false;
                    }
                    var data = result.data;
                    if (declareRealtyHouseCert.isEmpty(data)) {
                        $("#" + declareRealtyHouseCertConfig.frm).initForm(result.data);
                        $("#" + declareRealtyHouseCertConfig.frm + " input[name='registrationTime']").val(formatDate(data.registrationTime));
                        $("#" + declareRealtyHouseCertConfig.frm + " input[name='useEndDate']").val(formatDate(data.useEndDate));
                        $("#" + declareRealtyHouseCertConfig.frm + " input[name='useStartDate']").val(formatDate(data.useStartDate));
                        $("#" + declareRealtyHouseCertConfig.frm + " input[name='registrationDate']").val(formatDate(data.registrationDate));
                        $("#" + declareRealtyHouseCertConfig.frm + " input[name='landRegistrationDate']").val(formatDate(data.landRegistrationDate));
                        declareRealtyHouseCert.objectWriteSelectData(declareRealtyHouseCertConfig.frm, data.type, "type");
                        declareRealtyHouseCert.fileUpload(declareRealtyHouseCertConfig.fileId, AssessDBKey.DeclareRealtyHouseCert, data.id);
                        declareRealtyHouseCert.showFile(declareRealtyHouseCertConfig.fileId, AssessDBKey.DeclareRealtyHouseCert, data.id);
                        AssessCommon.initAreaInfo({
                            provinceTarget: $("#" + declareRealtyHouseCertConfig.frm + "province"),
                            cityTarget: $("#" + declareRealtyHouseCertConfig.frm + "city"),
                            districtTarget: $("#" + declareRealtyHouseCertConfig.frm + "district"),
                            provinceValue: result.data.province,
                            cityValue: result.data.city,
                            districtValue: result.data.district
                        });
                    }
                    //使校验生效
                    $("#" + declareRealtyHouseCertConfig.frm).validate();
                    $('#' + declareRealtyHouseCertConfig.box).modal("show");
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    };

    /**
     * @author:  zch
     * 描述:房产证 显示
     * @date:2018-09-19
     **/
    declareRealtyHouseCert.showAddModel = function () {
        $("#" + declareRealtyHouseCertConfig.frm).clearAll();
        if (declareRealtyHouseCert.declareRealtyHouseCertFlag) {
            declareRealtyHouseCert.init();
            declareRealtyHouseCert.declareRealtyHouseCertFlag = false;
        }
        declareRealtyHouseCert.fileUpload(declareRealtyHouseCertConfig.fileId, AssessDBKey.DeclareRealtyHouseCert, 0);
        declareRealtyHouseCert.showFile(declareRealtyHouseCertConfig.fileId, AssessDBKey.DeclareRealtyHouseCert, 0);
        //使校验生效
        $("#" + declareRealtyHouseCertConfig.frm).validate();
        $('#' + declareRealtyHouseCertConfig.box).modal("show");
    };

    /**
     * @author:  zch
     * 描述:房产证 初始化
     * @date:2018-09-19
     **/
    declareRealtyHouseCert.init = function () {
        AssessCommon.initAreaInfo({
            provinceTarget: $("#" + declareRealtyHouseCertConfig.frm + "province"),
            cityTarget: $("#" + declareRealtyHouseCertConfig.frm + "city"),
            districtTarget: $("#" + declareRealtyHouseCertConfig.frm + "district"),
            provinceValue: '',
            cityValue: '',
            districtValue: ''
        });
        AssessCommon.getProjectClassifyListByFieldName(AssessProjectClassifyKey.singleHousePropertyCertificateTypeCategory, function (html, data) {
            $("#" + declareRealtyHouseCertConfig.frm).find('select.type').empty().html(html).trigger('change');
        });
        declareRealtyHouseCert.role.writeCertName.init();
        declareRealtyHouseCert.role.beLocated.init();
    };

    /**
     * @author:  zch
     * 描述:房产证 列表加载
     * @date:2018-09-19
     **/
    declareRealtyHouseCert.loadList = function () {
        var cols = [];
        cols.push({field: 'provinceName', title: '省'});
        cols.push({field: 'cityName', title: '市'});
        cols.push({field: 'districtName', title: '县'});
        cols.push({field: 'floorArea', title: '建筑面积'});
        cols.push({field: 'certName', title: '房屋权证号'});
        cols.push({field: 'beLocated', title: '房屋坐落'});
        cols.push({field: 'fileViewName', title: '附件'});
        cols.push({
            field: 'id', title: '是否关联土地证', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                if (declareRealtyHouseCert.isEmpty(row.pid)) {
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
                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="declareRealtyHouseCert.editData(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips"  data-toggle="modal" data-placement="top" data-original-title="删除" onclick="declareRealtyHouseCert.deleteData(' + row.id + ',\'\')"><i class="fa fa-minus fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-success" href="javascript:declareRealtyHouseCert.sonDeclareRealtyLandCert.showViewModel(' + row.id + ');" ><i class="fa">土地证关联</i></a>';
                str += "<a class='btn btn-xs btn-success tooltips' data-placement='top' data-original-title='房产证附件' onclick='declareRealtyHouseCert.houseEnclosure(" + row.id + ")'" + ">" + "<i class='fa'>" + "房产证附件" + "</a>";
                str += "<a class='btn btn-xs btn-success tooltips' data-placement='top' data-original-title='土地证附件' onclick='declareRealtyHouseCert.landEnclosure(" + row.id + ")'" + ">" + "<i class='fa'>" + "土地证附件" + "</a>";
                str += '</div>';
                return str;
            }
        });
        $("#" + declareRealtyHouseCertConfig.table).bootstrapTable('destroy');
        TableInit(declareRealtyHouseCertConfig.table, "${pageContext.request.contextPath}/declareRealtyHouseCert/getDeclareRealtyHouseCertList", cols, {
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
     * 描述:房产证 识别
     * @date:2018-09-19
     **/
    declareRealtyHouseCert.distinguish = function () {
        var startPath = declareRealtyHouseCert.startPath;
        if (!declareRealtyHouseCert.isEmpty(startPath)) {
            toastr.success('稍后再试!');
            return false;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/declareRealtyHouseCert/parseRealtyHouseCert",
            type: "POST",
            dataType: "json",
            data: {startPath: startPath},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    $("#" + declareRealtyHouseCertConfig.frm).initForm(data);
                    $("#" + declareRealtyHouseCertConfig.frm + " input[name='registrationDate']").val(formatDate(data.registrationDate));
                    declareRealtyHouseCert.startPath = null;
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    };

    declareRealtyHouseCert.inputFileLand = function () {
        $.ajaxFileUpload({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareRealtyHouseCert/importDataLand",
            data: {
                planDetailsId: ${empty projectPlanDetails.id?0:projectPlanDetails.id}
            },//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: 'ajaxFileUploadHouseLand',//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                if (result.ret) {
                    declareRealtyHouseCert.loadList();
                    Alert(result.data);
                }
            },
            error: function (result, status, e) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    declareRealtyHouseCert.inputFile = function () {
        $.ajaxFileUpload({
            type: "POST",
            url: "${pageContext.request.contextPath}/declareRealtyHouseCert/importData",
            data: {
                planDetailsId: ${empty projectPlanDetails.id?0:projectPlanDetails.id}
            },//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: 'ajaxFileUploadHouse',//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                if (result.ret) {
                    declareRealtyHouseCert.loadList();
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
     * 描述:房产证附件 页面
     * @date:2018-10-23
     **/
    declareRealtyHouseCert.houseEnclosure = function (id) {
        $("#" + declareRealtyHouseCertConfig.fileIdNew).attr("data-id", id);
        $("#" + declareRealtyHouseCertConfig.fileIdNew).trigger('click');
    };
    /**
     * @author:  zch
     * 描述:附件处理服务端
     * @date:2018-10-23
     **/
    declareRealtyHouseCert.enclosureServer = function () {
        var id = $("#" + declareRealtyHouseCertConfig.fileIdNew).attr("data-id");
        $.ajaxFileUpload({
            type: "POST",
            url: "${pageContext.request.contextPath}/public/importAjaxFile",
            data: {
                planDetailsId: ${empty projectPlanDetails.id?0:projectPlanDetails.id},
                tableName: AssessDBKey.DeclareRealtyHouseCert,
                tableId: id,
                fieldsName: declareRealtyHouseCertConfig.fileId
            },//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: declareRealtyHouseCertConfig.fileIdNew,//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                if (result.ret) {
                    declareRealtyHouseCert.loadList();
                }
            },
            error: function (result, status, e) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };


    declareRealtyHouseCert.enclosureServer2 = function () {
        var id = $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.fileId).attr("data-id");
        $.ajaxFileUpload({
            type: "POST",
            url: "${pageContext.request.contextPath}/public/importAjaxFile",
            data: {
                planDetailsId: ${empty projectPlanDetails.id?0:projectPlanDetails.id},
                tableName: AssessDBKey.DeclareRealtyLandCert,
                tableId: id,
                fieldsName: declareRealtyHouseCertConfig.landFileId
            },//要传到后台的参数，没有可以不写
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: declareRealtyHouseCertConfig.son.declareRealtyLandCert.fileId,//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                if (result.ret) {
                    declareRealtyHouseCert.loadList();
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
     * 描述:土地证附件 页面
     * @date:2018-10-23
     **/
    declareRealtyHouseCert.landEnclosure = function (id) {
        var item = $("#" + declareRealtyHouseCertConfig.table).bootstrapTable('getRowByUniqueId', id);
        if (declareRealtyHouseCert.isEmpty(item.pid)) {
            $.ajax({
                url: "${pageContext.request.contextPath}/declareRealtyLandCert/getDeclareRealtyLandCertById",
                type: "get",
                dataType: "json",
                async:false,
                data: {id: item.pid, planDetailsId: '${empty projectPlanDetails.id?0:projectPlanDetails.id}'},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (declareRealtyHouseCert.isEmpty(data)) {
                            $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.fileId).attr("data-id", data.id);
                            $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.fileId).trigger('click');
                        } else {
                            toastr.success('关联的土地证数据已经被删除!');
                            toastr.success('请重新填写!');
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        } else {
            toastr.success('没有关联土地证数据!');
        }

    };

    /**
     * @author:  zch
     * 描述:房产证 格式化某些input
     * @date:2018-09-19
     **/
    declareRealtyHouseCert.role = {
        //房产权证号
        writeCertName: {
            init: function () {
                $("#" + declareRealtyHouseCertConfig.frm + " input[name='location']").blur(function () {
                    declareRealtyHouseCert.role.writeCertName.write();
                });
                $("#" + declareRealtyHouseCertConfig.frm + " input[name='number']").blur(function () {
                    declareRealtyHouseCert.role.writeCertName.write();
                });
                $("#" + declareRealtyHouseCertConfig.frm + " .type").change(function () {
                    /**
                     * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
                     **/
                    var id = $("#" + declareRealtyHouseCertConfig.frm + " .type").eq(1).val();
                    if (declareRealtyHouseCert.isEmpty(id)) {
                        declareRealtyHouseCert.role.writeCertName.write();
                    }
                });
            },
            //拼接 房产权证号
            write: function () {
                var location = $("#" + declareRealtyHouseCertConfig.frm + " input[name='location']").val();
                var number = $("#" + declareRealtyHouseCertConfig.frm + " input[name='number']").val();
                var id = $("#" + declareRealtyHouseCertConfig.frm + " .type").eq(1).val();
                if (!declareRealtyHouseCert.isEmpty(location)) {
                    location = "";
                }
                if (!declareRealtyHouseCert.isEmpty(number)) {
                    number = "";
                }
                if (!declareRealtyHouseCert.isEmpty(id)) {
                    id = "";
                }
                if (declareRealtyHouseCert.isEmpty(id)) {
                    AssessCommon.getProjectClassifyInfo(id, function (data) {
                        if (declareRealtyHouseCert.isEmpty(data)) {
                            var temp = location + "房权证" + data.name + "字地" + number + "号";
                            $("#" + declareRealtyHouseCertConfig.frm + " input[name='certName']").val(temp);
                        }
                        console.log(data);
                    });
                } else {
                    var temp = location + "房权证" + id + "字地" + number + "号";
                    $("#" + declareRealtyHouseCertConfig.frm + " input[name='certName']").val(temp);
                }
            }
        },
        //房屋坐落
        beLocated: {
            init: function () {
                $("#" + declareRealtyHouseCertConfig.frm + " input[name='unit']").blur(function () {
                    declareRealtyHouseCert.role.beLocated.write();
                });
                $("#" + declareRealtyHouseCertConfig.frm + " input[name='floor']").blur(function () {
                    declareRealtyHouseCert.role.beLocated.write();
                });
                $("#" + declareRealtyHouseCertConfig.frm + " input[name='roomNumber']").blur(function () {
                    declareRealtyHouseCert.role.beLocated.write();
                });
                $("#" + declareRealtyHouseCertConfig.frm + " input[name='streetNumber']").blur(function () {
                    declareRealtyHouseCert.role.beLocated.write();
                });
                $("#" + declareRealtyHouseCertConfig.frm + " input[name='attachedNumber']").blur(function () {
                    declareRealtyHouseCert.role.beLocated.write();
                });
                $("#" + declareRealtyHouseCertConfig.frm + " input[name='buildingNumber']").blur(function () {
                    declareRealtyHouseCert.role.beLocated.write();
                });
                $("#" + declareRealtyHouseCertConfig.frm + " .district").change(function () {
                    /**
                     * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
                     **/
                    var id = $("#" + declareRealtyHouseCertConfig.frm + " .district").eq(1).val();
                    if (declareRealtyHouseCert.isEmpty(id)) {
                        declareRealtyHouseCert.role.beLocated.write();
                    }
                });
            },
            //房屋坐落 拼接
            write: function () {
                var temp = null;
                var district = $("#" + declareRealtyHouseCertConfig.frm + " .district").eq(1).val();
                var unit = $("#" + declareRealtyHouseCertConfig.frm + " input[name='unit']").val();
                var floor = $("#" + declareRealtyHouseCertConfig.frm + " input[name='floor']").val();
                var roomNumber = $("#" + declareRealtyHouseCertConfig.frm + " input[name='roomNumber']").val();
                var streetNumber = $("#" + declareRealtyHouseCertConfig.frm + " input[name='streetNumber']").val();
                var attachedNumber = $("#" + declareRealtyHouseCertConfig.frm + " input[name='attachedNumber']").val();
                var buildingNumber = $("#" + declareRealtyHouseCertConfig.frm + " input[name='buildingNumber']").val();
                if (!declareRealtyHouseCert.isEmpty(unit)) {
                    unit = "";
                } else {
                    unit = unit + "单元";
                }
                if (!declareRealtyHouseCert.isEmpty(floor)) {
                    floor = "";
                } else {
                    floor = floor + "楼";
                }
                if (!declareRealtyHouseCert.isEmpty(roomNumber)) {
                    roomNumber = "";
                } else {
                    roomNumber = roomNumber + "号";
                }
                if (!declareRealtyHouseCert.isEmpty(streetNumber)) {
                    streetNumber = "";
                }
                if (!declareRealtyHouseCert.isEmpty(attachedNumber)) {
                    attachedNumber = "";
                } else {
                    attachedNumber = "附" + attachedNumber;
                }
                if (!declareRealtyHouseCert.isEmpty(buildingNumber)) {
                    buildingNumber = "";
                } else {
                    buildingNumber = buildingNumber + "栋";
                }
                if (declareRealtyHouseCert.isEmpty(district)) {
                    AssessCommon.getAreaById(district, function (data) {
                        if (!declareRealtyHouseCert.isEmpty(data)) {
                            district = "";
                        } else {
                            district = data.name;
                        }
                        temp = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                        $("#" + declareRealtyHouseCertConfig.frm + " input[name='beLocated']").val(temp);
                    });
                } else {
                    district = "";
                    temp = district + streetNumber + attachedNumber + buildingNumber + unit + floor + roomNumber;
                    $("#" + declareRealtyHouseCertConfig.frm + " input[name='beLocated']").val(temp);
                }
            }
        }
    };


    /**
     * @author:  zch
     * 描述:房产证 挂的土地证证(属子类)
     * @date:2018-09-19
     **/
    declareRealtyHouseCert.sonDeclareRealtyLandCert = {
        showViewModel: function (id) {
            if (declareRealtyHouseCert.sonDeclareRealtyLandCertFlag) {
                declareRealtyHouseCert.sonDeclareRealtyLandCert.init();
                declareRealtyHouseCert.sonDeclareRealtyLandCertFlag = false;
            }
            $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm).clearAll();
            $('#' + declareRealtyHouseCertConfig.son.declareRealtyLandCert.box).modal("show");
            var item = $("#" + declareRealtyHouseCertConfig.table).bootstrapTable('getRowByUniqueId', id);
            if (declareRealtyHouseCert.isEmpty(item.pid)) {
                //从服务端获取关联数据
                $.ajax({
                    url: "${pageContext.request.contextPath}/declareRealtyLandCert/getDeclareRealtyLandCertById",
                    type: "get",
                    dataType: "json",
                    data: {id: item.pid, planDetailsId: '${empty projectPlanDetails.id?0:projectPlanDetails.id}'},
                    success: function (result) {
                        if (result.ret) {
                            var data = result.data;
                            if (declareRealtyHouseCert.isEmpty(data)) {
                                AssessCommon.initAreaInfo({
                                    provinceTarget: $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + "province"),
                                    cityTarget: $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + "city"),
                                    districtTarget: $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + "district"),
                                    provinceValue: data.province,
                                    cityValue: data.city,
                                    districtValue: data.district
                                });
                                $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm).initForm(data);
                                $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + " input[name='terminationDate']").val(formatDate(data.terminationDate));
                                $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + " input[name='registrationDate']").val(formatDate(data.registrationDate));
                                declareRealtyHouseCert.objectWriteSelectData(declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm, data.type, "type");
                                declareRealtyHouseCert.objectWriteSelectData(declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm, data.purpose, "purpose");
                                declareRealtyHouseCert.objectWriteSelectData(declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm, data.useRightType, "useRightType");
                                declareRealtyHouseCert.showFile(declareRealtyHouseCertConfig.landFileId, AssessDBKey.DeclareRealtyLandCert, data.id);
                                declareRealtyHouseCert.fileUpload(declareRealtyHouseCertConfig.landFileId, AssessDBKey.DeclareRealtyLandCert, data.id);
                            } else {
                                toastr.success('关联的土地证数据已经被删除!');
                                toastr.success('请重新填写!');
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            } else {
                //把需要关联的数据带过来一些
                $.ajax({
                    url: "${pageContext.request.contextPath}/declareRealtyHouseCert/getDeclareRealtyHouseCertById",
                    type: "get",
                    dataType: "json",
                    data: {id: id},
                    success: function (resultA) {
                        if (resultA.ret) {
                            AssessCommon.initAreaInfo({
                                provinceTarget: $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + "province"),
                                cityTarget: $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + "city"),
                                districtTarget: $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + "district"),
                                provinceValue: resultA.data.province,
                                cityValue: resultA.data.city,
                                districtValue: resultA.data.district
                            });
                            $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm).initForm(
                                {
                                    pid: id,
                                    beLocated: resultA.data.beLocated,
                                    streetNumber: resultA.data.streetNumber,
                                    attachedNumber: resultA.data.attachedNumber,
                                    buildingNumber: resultA.data.buildingNumber,
                                    unit: resultA.data.unit,
                                    roomNumber: resultA.data.roomNumber,
                                    floor: resultA.data.floor
                                }
                            );
                            declareRealtyHouseCert.fileUpload(declareRealtyHouseCertConfig.landFileId, AssessDBKey.DeclareRealtyLandCert, 0);
                        }
                    },
                    error: function (resultA) {
                        Alert("调用服务端方法失败，失败原因:" + resultA);
                    }
                })
            }
        },
        saveAndUpdateData: function () {
            if (!$("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm).valid()) {
                return false;
            }
            var data = formParams(declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm);
            <%--data.planDetailsId = '${empty projectPlanDetails.id?0:projectPlanDetails.id}';--%>
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/declareRealtyLandCert/saveAndUpdateDeclareRealtyLandCert",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('成功!');
                        $('#' + declareRealtyHouseCertConfig.son.declareRealtyLandCert.box).modal("hide");
                        declareRealtyHouseCert.loadList();
                    } else {
                        Alert("保存失败:" + result.errmsg);
                    }
                },
                error: function (e) {
                    Alert("调用服务端方法失败，失败原因:" + e);
                }
            });
        },
        init: function () {
            AssessCommon.getProjectClassifyListByFieldName(AssessProjectClassifyKey.singleLandPropertyCertificateTypeCategory, function (html, data) {
                $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm).find('select.type').empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.estate_total_land_use, "", function (html, data) {
                $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm).find('select.purpose').empty().html(html).trigger('change');
            });
            declareRealtyHouseCert.sonDeclareRealtyLandCert.role.landCertName.init();
        },
        role: {
            //土地权证号
            landCertName: {
                write: function () {
                    var id = $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + " .type").eq(1).val();
                    var year = $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + " input[name='year']").val();
                    var number = $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + " input[name='number']").val();
                    var location = $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + " input[name='location']").val();
                    if (declareRealtyHouseCert.isEmpty(id)) {
                        AssessCommon.getProjectClassifyInfo(id, function (data) {
                            if (declareRealtyHouseCert.isEmpty(data)) {
                                var temp = location + data.name + year + "第" + number + "号";
                                $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + " input[name='landCertName']").val(temp);
                            }
                        });
                    } else {
                        var temp = location + year + "第" + number + "号";
                        $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + " input[name='landCertName']").val(temp);
                    }
                },
                init: function () {
                    $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + " input[name='year']").blur(function () {
                        declareRealtyHouseCert.sonDeclareRealtyLandCert.role.landCertName.write();
                    });
                    $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + " input[name='number']").blur(function () {
                        declareRealtyHouseCert.sonDeclareRealtyLandCert.role.landCertName.write();
                    });
                    $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + " input[name='location']").blur(function () {
                        declareRealtyHouseCert.sonDeclareRealtyLandCert.role.landCertName.write();
                    });
                    $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + " .type").change(function () {
                        /**
                         * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
                         **/
                        var id = $("#" + declareRealtyHouseCertConfig.son.declareRealtyLandCert.frm + " .type").eq(1).val();
                        if (declareRealtyHouseCert.isEmpty(id)) {
                            declareRealtyHouseCert.sonDeclareRealtyLandCert.role.landCertName.write();
                        }
                    });
                }
            }
        }
    };

    $(function () {
        declareRealtyHouseCert.loadList();
    });

</script>


<!-- 房产证信息 -->
<div id="boxDeclareRealtyHouseCert" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">房产证信息</h3>
            </div>
            <form id="frmDeclareRealtyHouseCert" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="pid">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            上传房产证<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <input id="declareRealtyHouseCertFileId" name="declareRealtyHouseCertFileId"
                                                   required="required" placeholder="上传房产证" class="form-control"
                                                   type="file">
                                            <div id="_declareRealtyHouseCertFileId"></div>
                                        </div>
                                    </div>
                                    <div class="col-sm-4">
                                        <label class="btn btn-default" onclick="declareRealtyHouseCert.distinguish();">识别</label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">省
                                            <span class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select name="province" id="frmDeclareRealtyHouseCertprovince"
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
                                            <select id="frmDeclareRealtyHouseCertcity" name="city"
                                                    class="form-control search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>

                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">县<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select id="frmDeclareRealtyHouseCertdistrict" name="district"
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
                                        <label class="col-sm-1 control-label">类型<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-3">
                                            <select name="type" class="form-control search-select select2 type"
                                                    required="required">
                                            </select>
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
                                        <label class="col-sm-1 control-label">房屋性质</label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="房屋性质" name="nature" class="form-control">
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
                                        <label class="col-sm-1 control-label">证载面积</label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="证载面积(数字)" name="evidenceArea" class="form-control"
                                                   data-rule-maxlength="100"
                                                   data-rule-number='true'>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">套内面积</label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="套内面积(数字)" name="innerArea" class="form-control"
                                                   data-rule-maxlength="100"
                                                   data-rule-number='true'>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">其它</label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="其它" name="other" class="form-control">
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
                                        <label class="col-sm-1 control-label">土地取得方式</label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="土地取得方式" name="landAcquisition" class="form-control">
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
                                        <label class="col-sm-1 control-label">附记其它</label>
                                        <div class="col-sm-3">
                                            <input type="text"
                                                   placeholder="附记其它" name="otherNote" class="form-control">
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
                        onclick="declareRealtyHouseCert.saveAndUpdate();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>


<!-- 土地证 add -->
<div id="boxSonDeclareRealtyLandCert" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土地证信息</h3>
            </div>
            <form id="frmSonDeclareRealtyLandCert" class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="pid">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            省<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <select name="province" id="frmSonDeclareRealtyLandCertprovince"
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
                                            <select id="frmSonDeclareRealtyLandCertcity" name="city"
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
                                            <select id="frmSonDeclareRealtyLandCertdistrict" name="district"
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
                                        <label class="col-sm-1 control-label">
                                            上传土地证附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <input id="declareRealtyHouseCert_land_FileId"
                                                   name="declareRealtyHouseCert_land_FileId"
                                                   required="required" placeholder="上传土地证附件" class="form-control"
                                                   type="file">
                                            <div id="_declareRealtyHouseCert_land_FileId"></div>
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
                            onclick="declareRealtyHouseCert.sonDeclareRealtyLandCert.saveAndUpdateData();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

