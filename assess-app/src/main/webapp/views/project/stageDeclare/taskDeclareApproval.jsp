<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>
            <!-- 申报各种类型的html视图 -->
            <%@include file="/views/project/stageDeclare/declareApprovalModel.jsp" %>
            <!-- 房产证 -->
            <div class="x_panel" id="viewDeclareRealtyHouseCert">
                <div class="x_content">
                    <div class="x_title">
                        <h3>
                            房产证申报
                        </h3>
                        <div class="clearfix"></div>
                    </div>
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class="x-valid">
                                <table class="table table-bordered" id="tableDeclareRealtyHouseCert">
                                </table>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <!-- 土地证 -->
            <div class="x_panel" id="viewDeclareRealtyLandCert">
                <div class="x_content">
                    <div class="x_title">
                        <h3>
                            土地证
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

            <!-- 不动产证 -->
            <div class="x_panel" id="viewDeclareRealtyRealEstateCert">
                <div class="x_content">
                    <div class="x_title">
                        <h3>
                            不动产证
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
            <div class="x_panel">
                <div class="x_content form-horizontal">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                估价委托书
                            </label>
                            <div class="col-md-5 col-sm-5 col-xs-12">
                                <div id="_project_proxy"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<script>
    var declareRealtyHouseCert = {};

    declareRealtyHouseCert.config = {
        frm: "frmDeclareRealtyHouseCert",
        name: "房产证",
        table: "tableDeclareRealtyHouseCert",
        box: "boxDeclareRealtyHouseCert",
        fileId: "declareRealtyHouseCertFileId",
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

    declareRealtyHouseCert.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

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
     * 描述:房产证 编辑
     * @date:2018-09-19
     **/
    declareRealtyHouseCert.findData = function (id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/declareRealtyHouseCert/getDeclareRealtyHouseCertById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    $('#' + declareRealtyHouseCert.config.box).find("#" + commonDeclareApprovalModel.config.house.handleId).remove();
                    $('#' + declareRealtyHouseCert.config.box).find(".panel-body").append(commonDeclareApprovalModel.house.getHtml());
                    $("#" + declareRealtyHouseCert.config.frm).clearAll();
                    if (result.data) {
                        $("#" + declareRealtyHouseCert.config.frm).initForm(result.data);
                        declareRealtyHouseCert.showFile(declareRealtyHouseCert.config.fileId,AssessDBKey.DeclareRealtyHouseCert,id);
                        $("#" + declareRealtyHouseCert.config.frm).find("label[name='registrationTime']").html(formatDate(result.data.registrationTime));
                        $("#" + declareRealtyHouseCert.config.frm).find("label[name='useEndDate']").html(formatDate(result.data.useEndDate));
                        $("#" + declareRealtyHouseCert.config.frm).find("label[name='useStartDate']").html(formatDate(result.data.useStartDate));
                        $("#" + declareRealtyHouseCert.config.frm).find("label[name='registrationDate']").html(formatDate(result.data.registrationDate));
                        $("#" + declareRealtyHouseCert.config.frm).find("label[name='landRegistrationDate']").html(formatDate(result.data.landRegistrationDate));
                    }
                    $('#' + declareRealtyHouseCert.config.box).modal("show");
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    };

    /**
     * @author:  zch
     * 描述:房产证 列表加载
     * @date:2018-09-19
     **/
    declareRealtyHouseCert.loadList = function () {
        var cols = commonDeclareApprovalModel.house.getHouseColumn();
        cols.push({field: 'fileViewName', title: '附件'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                if (row.pid) {
                    str += '<a class="btn btn-xs btn-success" href="javascript:declareRealtyHouseCert.sonDeclareRealtyLandCert.showViewModel(' + row.id + ');" ><i class="fa fa-check">土地证</i></a>';
                }
                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="查看" onclick="declareRealtyHouseCert.findData(' + row.id + ',\'tb_List\')">房产证<i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#" + declareRealtyHouseCert.config.table).bootstrapTable('destroy');
        TableInit(declareRealtyHouseCert.config.table, "${pageContext.request.contextPath}/declareRealtyHouseCert/getDeclareRealtyHouseCertList", cols, {
            planDetailsId: ${empty projectPlanDetails.id?0:projectPlanDetails.id},enable:'yes'
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
     * 描述:房产证 挂的土地证证(属子类)
     * @date:2018-09-19
     **/
    declareRealtyHouseCert.sonDeclareRealtyLandCert = {
        showViewModel: function (id) {
            var item = $("#" + declareRealtyHouseCert.config.table).bootstrapTable('getRowByUniqueId', id);
            if (declareRealtyHouseCert.isNotBlank(item.pid)) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/declareRealtyLandCert/getDeclareRealtyLandCertById",
                    type: "get",
                    dataType: "json",
                    data: {id: item.pid, planDetailsId: '${empty projectPlanDetails.id?0:projectPlanDetails.id}'},
                    success: function (result) {
                        if (result.ret) {
                            if (declareRealtyHouseCert.isNotBlank(result.data)) {
                                $('#' + declareRealtyHouseCert.config.son.declareRealtyLandCert.box).find("#" + commonDeclareApprovalModel.config.land.handleId).remove();
                                $('#' + declareRealtyHouseCert.config.son.declareRealtyLandCert.box).find(".panel-body").append(commonDeclareApprovalModel.land.getHtml());
                                $("#" + declareRealtyHouseCert.config.son.declareRealtyLandCert.frm).clearAll();
                                $("#" + declareRealtyHouseCert.config.son.declareRealtyLandCert.frm).initForm(result.data);
                                declareRealtyHouseCert.showFile(declareRealtyHouseCert.config.son.declareRealtyLandCert.fileId,AssessDBKey.DeclareRealtyLandCert,result.data.id);
                                $("#" + declareRealtyHouseCert.config.son.declareRealtyLandCert.frm).find("label[name='terminationDate']").html(formatDate(result.data.terminationDate));
                                $("#" + declareRealtyHouseCert.config.son.declareRealtyLandCert.frm).find("label[name='registrationDate']").html(formatDate(result.data.registrationDate));
                                $('#' + declareRealtyHouseCert.config.son.declareRealtyLandCert.box).modal("show");
                            } else {
                                toastr.success('关联的土地证数据已经被删除!');
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            } else {
                Alert("无关联数据");
            }
        }
    };

    $(function () {
        declareRealtyHouseCert.loadList();
        loadUploadFiles(AssessDBKey.ProjectInfo, "${projectPlanDetails.projectId}", AssessUploadKey.PROJECT_PROXY);
    });
</script>
<script type="text/javascript">
    //显示附件
    function loadUploadFiles(tableName, tableId, fieldsName, target) {
        FileUtils.getFileShows({
            target: target == undefined ? fieldsName : target,
            formData: {
                tableName: tableName,
                tableId: tableId,
                fieldsName: fieldsName,
                projectId: "${projectPlanDetails.projectId}"
            },
            editFlag: false,
            deleteFlag: false
        })
    }

    var declareRealtyLandCert = {} ;
    declareRealtyLandCert.config = {
        frm: "frmDeclareRealtyLandCert",
        name: "土地证",
        table: "tableDeclareRealtyLandCert",
        box: "boxDeclareRealtyLandCert",
        fileId: "declareRealtyLandCertFileId",
        fileView: "declareRealtyLandCertFileView",
        houseFileId: "declareRealtyLandCert_declareRealtyLandCert_HouseCert_FileId",
        houseView: "declareRealtyLandCert_declareRealtyLandCert_HouseCert_View",
        HouseCert: {
            frm: "declareRealtyLandCert_HouseCert_frm",
            box: "declareRealtyLandCert_HouseCert_box"
        }
    } ;


    declareRealtyLandCert.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
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
        })
    };



    declareRealtyLandCert.findData = function (id) {
        $('#' + declareRealtyLandCert.config.box).find("#" + commonDeclareApprovalModel.config.land.handleId).remove();
        $('#' + declareRealtyLandCert.config.box).find(".panel-body").append(commonDeclareApprovalModel.land.getHtml());
        $("#" + declareRealtyLandCert.config.frm).clearAll();
        $('#' + declareRealtyLandCert.config.box).modal("show");
        $.ajax({
            url: "${pageContext.request.contextPath}/declareRealtyLandCert/getDeclareRealtyLandCertById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    if (declareRealtyLandCert.isNotBlank(data)) {
                        $("#" + declareRealtyLandCert.config.frm).initForm(result.data);
                        declareRealtyLandCert.showFile(declareRealtyLandCert.config.fileId,AssessDBKey.DeclareRealtyLandCert,id) ;
                        $("#" + declareRealtyLandCert.config.frm).find("label[name='terminationDate']").html(formatDate(result.data.terminationDate));
                        $("#" + declareRealtyLandCert.config.frm).find("label[name='registrationDate']").html(formatDate(result.data.registrationDate));
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    /**
     * @author:  zch
     * 描述:房产证数据
     * @date:2018-09-21
     **/
    declareRealtyLandCert.houseCard = function (id) {
        $('#' + declareRealtyLandCert.config.HouseCert.box).find("#" + commonDeclareApprovalModel.config.house.handleId).remove();
        $('#' + declareRealtyLandCert.config.HouseCert.box).find(".panel-body").append(commonDeclareApprovalModel.house.getHtml());
        $("#" + declareRealtyLandCert.config.HouseCert.frm).clearAll();
        var item = $("#" + declareRealtyLandCert.config.table).bootstrapTable('getRowByUniqueId', id);
        if (declareRealtyLandCert.isNotBlank(item.pid)) {
            $('#' + declareRealtyLandCert.config.HouseCert.box).modal("show");
            $.ajax({
                url: "${pageContext.request.contextPath}/declareRealtyHouseCert/getDeclareRealtyHouseCertById",
                type: "get",
                dataType: "json",
                data: {id: item.pid, planDetailsId: '${empty projectPlanDetails.id?0:projectPlanDetails.id}'},
                success: function (result) {
                    if (result.ret) {
                        if (declareRealtyLandCert.isNotBlank(result.data)) {
                            $("#" + declareRealtyLandCert.config.HouseCert.frm).initForm(result.data);
                            declareRealtyLandCert.showFile(declareRealtyLandCert.config.houseFileId,AssessDBKey.DeclareRealtyHouseCert,result.data.id) ;
                            $("#" + declareRealtyLandCert.config.HouseCert.frm).find("label[name='registrationTime']").html(formatDate(result.data.registrationTime));
                            $("#" + declareRealtyLandCert.config.HouseCert.frm).find("label[name='useEndDate']").html(formatDate(result.data.useEndDate));
                            $("#" + declareRealtyLandCert.config.HouseCert.frm).find("label[name='useStartDate']").html(formatDate(result.data.useStartDate));
                            $("#" + declareRealtyLandCert.config.HouseCert.frm).find("label[name='registrationDate']").html(formatDate(result.data.registrationDate));
                            $("#" + declareRealtyLandCert.config.HouseCert.frm).find("label[name='landRegistrationDate']").html(formatDate(result.data.landRegistrationDate));
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
        }
    };

    declareRealtyLandCert.loadList = function () {
        var cols = commonDeclareApprovalModel.land.getLandColumn();
        cols.push({field: 'fileViewName', title: '附件'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                if (row.pid) {
                    str += '<a class="btn btn-xs btn-success" href="javascript:declareRealtyLandCert.houseCard(' + row.id + ');" ><i class="fa fa-check">房产证关联数据查看详情</i></a>';
                }
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="土地证查看详情" onclick="declareRealtyLandCert.findData(' + row.id + ',\'tb_List\')">土地证<i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#" + declareRealtyLandCert.config.table).bootstrapTable('destroy');
        TableInit(declareRealtyLandCert.config.table, "${pageContext.request.contextPath}/declareRealtyLandCert/getDeclareRealtyLandCertList", cols, {
            planDetailsId: '${empty projectPlanDetails.id?0:projectPlanDetails.id}' , enable:'yes'
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
        declareRealtyLandCert.loadList();
    });
</script>
<script>

    var declareRealtyRealEstateCert = {} ;

    declareRealtyRealEstateCert.config = {
        frm: "frmDeclareRealtyRealEstateCert",
        name: "不动产证",
        table: "tableDeclareRealtyRealEstateCert",
        box: "boxDeclareRealtyRealEstateCert",
        fileId: "declareRealtyRealEstateCertFileId",
        newFileId: "declareRealtyRealEstateCertNewFileId",
        fileView: "declareRealtyRealEstateCertFileView"
    } ;

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

    declareRealtyRealEstateCert.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    declareRealtyRealEstateCert.findData = function (id) {
        $('#' + declareRealtyRealEstateCert.config.box).find("#" + commonDeclareApprovalModel.config.realEstateCert.handleId).remove();
        $('#' + declareRealtyRealEstateCert.config.box).find(".panel-body").append(commonDeclareApprovalModel.realEstateCert.getHtml());
        $("#" + declareRealtyRealEstateCert.config.frm).clearAll();
        $('#' + declareRealtyRealEstateCert.config.box).modal("show");
        $.ajax({
            url: "${pageContext.request.contextPath}/declareRealtyRealEstateCert/getDeclareRealtyRealEstateCertById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    if (declareRealtyRealEstateCert.isNotBlank(data)) {
                        $("#" + declareRealtyRealEstateCert.config.frm).initForm(result.data);
                        declareRealtyRealEstateCert.showFile(declareRealtyRealEstateCert.config.fileId,AssessDBKey.DeclareRealtyRealEstateCert,id);
                        $("#" + declareRealtyRealEstateCert.config.frm).find("label[name='registrationTime']").html(formatDate(result.data.registrationTime));
                        $("#" + declareRealtyRealEstateCert.config.frm).find("label[name='useEndDate']").html(formatDate(result.data.useEndDate));
                        $("#" + declareRealtyRealEstateCert.config.frm).find("label[name='useStartDate']").html(formatDate(result.data.useStartDate));
                        $("#" + declareRealtyRealEstateCert.config.frm).find("label[name='registrationDate']").html(formatDate(result.data.registrationDate));
                        $("#" + declareRealtyRealEstateCert.config.frm).find("label[name='terminationDate']").html(formatDate(result.data.terminationDate));
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    declareRealtyRealEstateCert.loadList = function () {
        var cols = commonDeclareApprovalModel.realEstateCert.getRealEstateColumn() ;
        cols.push({field: 'fileViewName', title: '不动产附件'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="查看详情" onclick="declareRealtyRealEstateCert.findData(' + row.id + ',\'exampleList\')"><i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#" + declareRealtyRealEstateCert.config.table).bootstrapTable('destroy');
        TableInit(declareRealtyRealEstateCert.config.table, "${pageContext.request.contextPath}/declareRealtyRealEstateCert/getDeclareRealtyRealEstateCertList", cols, {
            planDetailsId: '${empty projectPlanDetails.id?0:projectPlanDetails.id}' , enable:'yes'
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
        declareRealtyRealEstateCert.loadList();
    });
</script>

<script type="application/javascript">


    //提交审批
    function saveform() {
        saveApprovalform("");
    }

</script>
</body>

<!-- 土地证 房产证模块 -->
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
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="col-sm-5">
                                        <div id="_sonDeclareRealtyLandCertFileId"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        关闭
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>


<!-- 房产证信息 房产证模块 -->
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
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="col-sm-5">
                                        <div id="_declareRealtyHouseCertFileId"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 房产证信息 土地证模块-->
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
                                    <div class="col-sm-5">
                                        <div id="_declareRealtyLandCert_declareRealtyLandCert_HouseCert_FileId"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 土地证信息 土地证模块-->
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
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="col-sm-5">
                                        <div id="_declareRealtyLandCertFileId"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        关闭
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 不动产  -->
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
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="col-sm-5">
                                        <div id="_declareRealtyRealEstateCertFileId"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        关闭
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
</html>

