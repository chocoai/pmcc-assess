
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                        ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frmQuery" class="form-horizontal">
                        <div class="form-group ">
                            <div>
                                <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                    发文单位
                                </label>
                                <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="名称" id="queryName" name="queryName"
                                           class="form-control">
                                </div>
                            </div>

                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <button type="button" class="btn btn-primary"
                                        onclick="dataObjFun.loadDataList()">
                                    查询
                                </button>

                                <button type="button" class="btn btn-success"
                                        onclick="dataObjFun.showModel();"
                                        data-toggle="modal" href="#divBox"> 新增
                                </button>
                            </div>
                        </div>

                    </form>
                    <table class="table table-bordered" id="tb_FatherList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    $(function () {
        dataObjFun.event.father.init();
        dataObjFun.loadDataList();
    });
    var DataObjFun = function () {

    };
    DataObjFun.prototype.config = {
        father: {
            frm: function () {
                return "frmFather";
            },
            table: function () {
                return "tb_FatherList";
            },
            box: function () {
                return "divBoxFather";
            },
            fileId: "fileIdFatherInfrastructure"
        },

        infrastructureDevView: "infrastructureDevView",
        infrastructureDevView2: "infrastructureDevView2",
        infrastructureDevTable: "infrastructureDevTable",
        infrastructureDevFrm: "infrastructureDevFrm"
    };
    DataObjFun.prototype.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    };
    DataObjFun.prototype.objectWriteSelectData = function (frm, data, name) {
        if (DataObjFun.prototype.isEmpty(data)) {
            $("#" + frm + " ." + name).val(data).trigger("change");
        } else {
            $("#" + frm + " ." + name).val(null).trigger("change");
        }
    };
    DataObjFun.prototype.event = {
        father: {
            select2Load: function () {
                //使数据校验生效
                $("#" + dataObjFun.config.father.frm()).validate();
                AssessCommon.initAreaInfo({
                    provinceTarget: $("#province"),
                    cityTarget: $("#city"),
                    districtTarget: $("#district"),
                    provinceValue: '',
                    cityValue: '',
                    districtValue: ''
                })
            },
            init: function () {
                DataObjFun.prototype.event.father.select2Load();
            }
        }
    };

    var dataObjFun = new DataObjFun();

    /**
     * @author:  zch
     * 描述:文件显示
     * @date:2018-09-19
     **/
    dataObjFun.showFile = function (target, tableName, id) {
        FileUtils.getFileShows({
            target: target,
            formData: {
                fieldsName: target,
                tableName: tableName,
                tableId: id
            },
            deleteFlag: true
        })
    };

    /**
     * @author:  zch
     * 描述:文件上传
     * @date:2018-09-19
     **/
    dataObjFun.fileUpload = function (target, tableName, id) {
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
                dataObjFun.showFile(target, tableName, id);
            },
            deleteFlag: true
        });
    };

    dataObjFun.loadDataList = function () {
        var cols = [];
        cols.push({field: 'provinceName', title: '省'});
        cols.push({field: 'cityName', title: '市'});
        cols.push({field: 'districtName', title: '县'});
        cols.push({field: 'dispatchUnit', title: '发文单位'});
        cols.push({field: 'number', title: '文号'});
        cols.push({field: 'fileViewName', title: '附件'});
        cols.push({field: 'startDateName', title: '开始日期'});
        cols.push({field: 'endDateName', title: '结束日期'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="dataObjFun.editDataById(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="dataObjFun.deleteDataById(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-success" href="javascript:dataObjFun.dataInfrastructureChildrenShowView(' + row.id + ');" > 子类税费 </i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#" + dataObjFun.config.father.table()).bootstrapTable('destroy');
        TableInit(dataObjFun.config.father.table(), "${pageContext.request.contextPath}/infrastructure/getInfrastructureList", cols, {
            dispatchUnit: $("#queryName").val()
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
     * 描述:删除
     * @date:
     **/
    dataObjFun.deleteDataById = function (id) {
        Alert("是否删除", 2, null, function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/infrastructure/deleteInfrastructureById",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('删除成功');
                        dataObjFun.loadDataList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        });
    };

    /**
     * @author:  zch
     * 描述:编辑
     * @date:
     **/
    dataObjFun.editDataById = function (id) {
        var frm = $("#" + dataObjFun.config.father.frm()) ;
        var data = $("#" + dataObjFun.config.father.table()).bootstrapTable('getRowByUniqueId', id);
        frm.initForm(data);
        AssessCommon.initAreaInfo({
            provinceTarget: frm.find("select[name='province']"),
            cityTarget: frm.find("select[name='city']"),
            districtTarget: frm.find("select[name='district']"),
            provinceValue: data.province,
            cityValue: data.city,
            districtValue: data.district
        });
        frm.find("input[name='startDate']").val(formatDate(data.startDate));
        frm.find("input[name='endDate']").val(formatDate(data.endDate));
        dataObjFun.fileUpload(dataObjFun.config.father.fileId, "tb_data_infrastructure", id);
        dataObjFun.showFile(dataObjFun.config.father.fileId, "tb_data_infrastructure", id);
        $('#' + dataObjFun.config.father.box()).modal("show");
    };

    /**
     * @author:  zch
     * 描述:保存或者更新
     * @date:
     **/
    dataObjFun.saveAndUpdateData = function () {
        if (!$("#" + dataObjFun.config.father.frm()).valid()) {
            return false;
        }
        var data = formParams(dataObjFun.config.father.frm());
        $.ajax({
            url: "${pageContext.request.contextPath}/infrastructure/saveAndUpdateInfrastructure",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    toastr.success('保存成功');
                    $('#' + dataObjFun.config.father.box()).modal('hide');
                    dataObjFun.loadDataList();
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    };

    /**
     * @author:  zch
     * 描述:显示添加数据的模型
     * @date:
     **/
    dataObjFun.showModel = function () {
        $("#" + dataObjFun.config.father.frm()).clearAll();
        $('#' + dataObjFun.config.father.box()).modal("show");
        dataObjFun.fileUpload(dataObjFun.config.father.fileId, "tb_data_infrastructure", 0);
        dataObjFun.showFile(dataObjFun.config.father.fileId, "tb_data_infrastructure", 0);
    };


    //开发期间税费 方法
    dataObjFun.dataInfrastructureChildrenShowView = function (id) {
        $('#' + dataObjFun.config.infrastructureDevView).modal("show");
        dataObjFun.dataInfrastructureChildrenList(id);
        $('#' + dataObjFun.config.infrastructureDevView).find("input[name='pid']").val(id);
    };

    //开发期间税费 方法
    dataObjFun.dataInfrastructureChildrenSaveAndUpdate = function () {
        if (!$("#" + dataObjFun.config.infrastructureDevFrm).valid()) {
            return false;
        }
        var data = formParams(dataObjFun.config.infrastructureDevFrm);
        $.ajax({
            url: "${pageContext.request.contextPath}/dataInfrastructureChildren/saveAndUpdate",
            type: "post",
            dataType: "json",
            data: {formData:JSON.stringify(data)},
            success: function (result) {
                if (result.ret) {
                    toastr.success('成功');
                    dataObjFun.dataInfrastructureChildrenList(data.pid);
                    $('#' + dataObjFun.config.infrastructureDevView2).modal("hide");
                }
                else {
                    Alert("失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    };

    //开发期间税费 方法
    dataObjFun.dataInfrastructureChildrenDelete = function (id) {
        var item = $("#" + dataObjFun.config.infrastructureDevTable).bootstrapTable('getRowByUniqueId', id);
        Alert("是否删除", 2, null, function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/dataInfrastructureChildren/deleteData",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('删除成功');
                        dataObjFun.dataInfrastructureChildrenList(item.pid);
                    }
                    else {
                        Alert("失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        });
    };

    //开发期间税费 方法
    dataObjFun.dataInfrastructureChildrenList = function (pid) {
        var cols = [];
        cols.push({field: 'name', title: '名称'});
        cols.push({field: 'number', title: '金额'});
        cols.push({field: 'tax', title: '税费'});
        cols.push({field: 'type', title: '类型'});

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="dataObjFun.dataInfrastructureChildrenDelete(' + row.id + ',\'tb_List1\')"><i class="fa fa-minus fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="编辑" onclick="dataObjFun.editDataInfrastructureChildren(' + row.id + ',\'tb_List1\')"><i class="fa fa-edit"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#" + dataObjFun.config.infrastructureDevTable).bootstrapTable('destroy');
        TableInit(dataObjFun.config.infrastructureDevTable, "${pageContext.request.contextPath}/dataInfrastructureChildren/getBootstrapTableVo", cols, {
            pid: pid
        }, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    };

    dataObjFun.editDataInfrastructureChildren = function (id) {
        var item = $("#" + dataObjFun.config.infrastructureDevTable).bootstrapTable('getRowByUniqueId', id);
        $("#" + dataObjFun.config.infrastructureDevFrm).clearAll();
        $("#" + dataObjFun.config.infrastructureDevFrm).validate();
        $("#" + dataObjFun.config.infrastructureDevFrm).initForm(item);
        $('#' + dataObjFun.config.infrastructureDevView2).modal("show");
    } ;

    //开发期间税费 方法
    dataObjFun.dataInfrastructureChildrenAddView = function () {
        $('#' + dataObjFun.config.infrastructureDevView2).modal("show");
        var pid = $('#' + dataObjFun.config.infrastructureDevView).find("input[name='pid']").val();
        $("#" + dataObjFun.config.infrastructureDevFrm).clearAll();
        $("#" + dataObjFun.config.infrastructureDevFrm).initForm({pid: pid});
        $("#" + dataObjFun.config.infrastructureDevFrm).validate();
    };


</script>

<!-- 子类税费 add -->
<div id="infrastructureDevView2" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">子类税费</h3>
            </div>
            <form id="infrastructureDevFrm" class="form-horizontal">
                <input type="hidden" name="pid">
                <input type="hidden" name="id">
                <div class="panel-body">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                名称<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                <input type="text" name="name" class="form-control" required="required"
                                       placeholder="名称">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                类型<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                <select required="required" class="search-select select2" name="type">
                                    <option value="1">开发期间税费</option>
                                    <option value="2">公共配套设施费用</option>
                                    <option value="3">基础配套设施费用</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                金额<span class="symbol required"></span>
                            </label>
                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                <input type="text" name="number" placeholder="金额" class="form-control"
                                       data-rule-number='true'
                                       required="required">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                税费
                            </label>
                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                <input type="text" name="tax" data-rule-number='true' class="form-control" placeholder="(数字)">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="dataObjFun.dataInfrastructureChildrenSaveAndUpdate()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 子类税费 list -->
<div id="infrastructureDevView" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">子类税费列表</h3>
                <input type="hidden" name="pid">
            </div>
            <div class="panel-body">
                <span>
                    <button type="button" class="btn btn-success"
                            data-toggle="modal" onclick="dataObjFun.dataInfrastructureChildrenAddView();"> 新增
                    </button>
                </span>
                <table class="table table-bordered" id="infrastructureDevTable">
                </table>
            </div>
        </div>
    </div>
</div>


<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">基础设施维护</h3>
            </div>
            <form id="frmFather" class="form-horizontal">
                <input type="hidden" id="id" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 control-label">省
                                            <span class="symbol required"></span></label>
                                        <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                            <select name="province" class="form-control search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 control-label">市<span
                                                class="symbol required"></span></label>
                                        <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                            <select  name="city" class="form-control search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 control-label">县</label>
                                        <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                            <select  name="district"
                                                    class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 control-label">
                                            项目类别<span class="symbol required"></span>
                                        </label>
                                        <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                            <input type="text" class="form-control" name="projectType"
                                                   placeholder="项目类别" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 control-label">
                                            发文单位<span class="symbol required"></span>
                                        </label>
                                        <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                            <input type="text" class="form-control" name="dispatchUnit"
                                                   placeholder="发文单位" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 control-label">
                                            文号<span class="symbol required"></span>
                                        </label>
                                        <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                            <input type="text" class="form-control" data-rule-number='true'
                                                   name="number"
                                                   placeholder="文号" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 control-label">
                                            开始日期<span class="symbol required"></span>
                                        </label>
                                        <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                            <input type="text" readonly="readonly"
                                                   class="form-control date-picker dbdate" data-date-format="yyyy-mm-dd"
                                                   name="startDate"
                                                   placeholder="开始日期" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 control-label">
                                            结束日期<span class="symbol required"></span>
                                        </label>
                                        <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                            <input type="text" readonly="readonly"
                                                   class="form-control date-picker dbdate" data-date-format="yyyy-mm-dd"
                                                   name="endDate"
                                                   placeholder="结束日期" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 control-label">
                                            附件<span class="symbol required"></span>
                                        </label>
                                        <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                                            <input id="fileIdFatherInfrastructure" name="fileIdFatherInfrastructure"
                                                   required="required" placeholder="上传附件" class="form-control"
                                                   type="file">
                                            <div id="_fileIdFatherInfrastructure"></div>
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
                    <button type="button" class="btn btn-primary" onclick="dataObjFun.saveAndUpdateData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>
