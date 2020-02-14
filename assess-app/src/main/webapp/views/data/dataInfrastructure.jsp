
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body>
<div class="wrapper">
    <%@include file="/views/share/main_navigation.jsp" %>
    <%@include file="/views/share/main_head.jsp" %>
    <div class="main-panel">
        <div class="content">
            <div class="panel-header bg-primary-gradient">
                <div class="page-inner py-5">
                </div>
            </div>
            <div class="page-inner mt--5">
                <div class="row mt--2">

                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">${baseViewDto.currentMenu.name}</div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frmQuery" class="form-horizontal">
                                    <div class="form-group form-inline">
                                        <label for="queryName" class="col-md-1 col-form-label">发文单位</label>
                                        <div class="col-md-3 p-0">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="名称" id="queryName" name="queryName"
                                                   class="form-control input-full">
                                        </div>

                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="dataObjFun.loadDataList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                        <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                                data-toggle="modal" onclick="dataObjFun.showModel()"
                                                href="#divBoxFather">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                            新增
                                        </button>
                                    </div>


                                </form>
                                <table class="table table-bordered" id="tb_FatherList">
                                    <!-- cerare document add ajax data-->
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>

</body>

<script type="text/javascript">
    $(function () {
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

    DataObjFun.prototype.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
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
        cols.push({field: 'provinceName',width: '5%', title: '省'});
        cols.push({field: 'cityName',width: '5%',  title: '市'});
        cols.push({field: 'districtName',width: '5%',  title: '县'});
        cols.push({
            field: 'type',width: '10%',  title: '类型', formatter: function (value, row, index) {
                var str = '';
                if (row.type){
                    var data = JSON.parse('${JSONkeyValueDtos}') ;
                    $.each(data,function (i,item) {
                        if (row.type == item.key){
                            str += item.value ;
                        }
                    });
                }
                return str;
            }
        });
        cols.push({field: 'projectType', width: '10%', title: '项目类型'});
        cols.push({field: 'dispatchUnit',width: '10%',  title: '发文单位'});
        cols.push({field: 'number',width: '10%',  title: '文号'});
        cols.push({field: 'fileViewName', width: '10%', title: '附件'});
        cols.push({field: 'startDateName', width: '10%', title: '开始日期'});
        cols.push({field: 'endDateName', width: '10%', title: '结束日期'});
        cols.push({
            field: 'id',width: '10%',  title: '操作', formatter: function (value, row, index) {
                var str = '<button onclick="dataObjFun.dataInfrastructureChildrenShowView(' + row.id + ')" style="margin-left: 5px;" class="btn btn-icon btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="子类税费">';
                str += '<i class="fa fa-search"></i>';
                str += '</button>';
                str += '<button onclick="dataObjFun.editDataById(' + row.id + ')"  style="margin-left: 5px;"  class="btn btn-icon btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button onclick="dataObjFun.deleteDataById(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn btn-icon btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
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
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/infrastructure/deleteInfrastructureById",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除数据成功");
                        dataObjFun.loadDataList();
                    }
                    else {
                        AlertError("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
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
        frm.find("select[name='type']").val(data.type).trigger('change');
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
            data: {formData:JSON.stringify(data)},
            success: function (result) {
                if (result.ret) {
                    AlertSuccess("成功", "数据已成功保存到数据库");
                    $('#' + dataObjFun.config.father.box()).modal('hide');
                    dataObjFun.loadDataList();
                }
                else {
                    AlertError("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        })
    };

    /**
     * @author:  zch
     * 描述:显示添加数据的模型
     * @date:
     **/
    dataObjFun.showModel = function () {
        var frm = $("#" + dataObjFun.config.father.frm()) ;
        frm.clearAll();
        $('#' + dataObjFun.config.father.box()).modal("show");
        AssessCommon.initAreaInfo({
            provinceTarget: frm.find("select[name='province']"),
            cityTarget: frm.find("select[name='city']"),
            districtTarget: frm.find("select[name='district']"),
            provinceValue: null,
            cityValue: null,
            districtValue: null
        });
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
                    AlertSuccess("成功", "数据已成功保存到数据库");
                    dataObjFun.dataInfrastructureChildrenList(data.pid);
                    $('#' + dataObjFun.config.infrastructureDevView2).modal("hide");
                }
                else {
                    AlertError("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        })
    };

    //开发期间税费 方法
    dataObjFun.dataInfrastructureChildrenDelete = function (id) {
        var item = $("#" + dataObjFun.config.infrastructureDevTable).bootstrapTable('getRowByUniqueId', id);
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/dataInfrastructureChildren/deleteData",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除数据成功");
                        dataObjFun.dataInfrastructureChildrenList(item.pid);
                    }
                    else {
                        AlertError("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
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

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<button onclick="dataObjFun.editDataInfrastructureChildren(' + row.id + ')"  style="margin-left: 5px;"  class="btn btn-icon btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button onclick="dataObjFun.dataInfrastructureChildrenDelete(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn btn-icon btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
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


<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">基础设施维护</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmFather" class="form-horizontal">
                    <input type="hidden" id="id" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                省<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="province" class="form-control input-full search-select select2"
                                                        required="required">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                市<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select  name="city" class="form-control input-full search-select select2"
                                                         required="required">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                县
                                            </label>
                                            <div class="col-sm-10">
                                                <select  name="district"
                                                         class="form-control input-full search-select select2">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                项目类别
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full" name="projectType"
                                                       placeholder="项目类别">
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                类型<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select required="required" class="form-control input-full search-select select2" name="type">
                                                    <option>请选择</option>
                                                    <c:forEach var="item" items="${keyValueDtos}">
                                                        <option value="${item.key}">${item.value}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                发文单位<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full" name="dispatchUnit"
                                                       placeholder="发文单位" required="required">
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                文号
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full" name="number" placeholder="文号">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                开始日期<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" readonly="readonly"
                                                       class="form-control input-full date-picker dbdate" data-date-format="yyyy-mm-dd"
                                                       name="startDate"
                                                       placeholder="开始日期" required="required">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                结束日期
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" readonly="readonly"
                                                       class="form-control input-full date-picker dbdate" data-date-format="yyyy-mm-dd"
                                                       name="endDate"
                                                       placeholder="结束日期" required="required">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                文件名称
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" name="fileName"  class="form-control input-full" placeholder="文件名称">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 col-form-label">
                                                附件
                                            </label>
                                            <div class="col-sm-10">
                                                <input id="fileIdFatherInfrastructure" name="fileIdFatherInfrastructure"
                                                       required="required" placeholder="上传附件" class="form-control input-full"
                                                       type="file">
                                                <div id="_fileIdFatherInfrastructure"></div>                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="dataObjFun.saveAndUpdateData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<!-- 子类税费 list -->
<div id="infrastructureDevView" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">子类税费列表</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card-body">
                            <input type="hidden" name="pid">
                            <p>
                                <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                        data-toggle="modal" onclick="dataObjFun.dataInfrastructureChildrenAddView()"
                                        href="#infrastructureDevView2">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                    新增
                                </button>
                            </p>
                            <table class="table table-bordered" id="infrastructureDevTable">
                            </table>

                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
            </div>

        </div>
    </div>
</div>

<!-- 子类税费 add -->
<div id="infrastructureDevView2" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">子类税费</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="infrastructureDevFrm" class="form-horizontal">
                    <input type="hidden" name="pid">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" name="name" class="form-control input-full" required="required"
                                                       placeholder="名称">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                金额<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" name="number" placeholder="金额" class="form-control input-full"
                                                       data-rule-number='true'
                                                       required="required">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                税费
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" name="tax" data-rule-number='true' class="form-control input-full" placeholder="(数字)">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="dataObjFun.dataInfrastructureChildrenSaveAndUpdate()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>



</html>
