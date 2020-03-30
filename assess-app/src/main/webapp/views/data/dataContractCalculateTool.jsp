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
                                <c:if test="${readonly ne true}">
                                    <form id="frmQuery" class="form-horizontal">
                                        <div class="form-group form-inline">
                                            <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                                    data-toggle="modal" onclick="dataContractCalculateTool.prototype.showModel()"
                                                    href="#divBoxFather">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                                新增
                                            </button>
                                        </div>
                                    </form>
                                </c:if>
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
        dataContractCalculateTool.prototype.loadDataDicList();
    });
    var dataContractCalculateTool = function () {

    };
    dataContractCalculateTool.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_FatherList";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            return data;
        },
        loadDataDicList: function () {
            var cols = [];
            cols.push({field: 'referenceNumber',width: '30%', title: '文号'});
            cols.push({field: 'customerName',width: '30%', title: '客户名称'});
            cols.push({field: 'fileViewName', width: '30%',title: '附件'});
            <c:if test="${readonly ne true}">
            cols.push({
                field: 'id', width: '10%',title: '操作', formatter: function (value, row, index) {
                    var str = '<button onclick="dataContractCalculateTool.prototype.getAndInit(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                    str += '<i class="fa fa-pen"></i>';
                    str += '</button>';
                    str += '<button onclick="dataContractCalculateTool.prototype.removeData(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                    str += '<i class="fa fa-minus"></i>';
                    str += '</button>';
                    return str;
                }
            });
            </c:if>
            $("#" + dataContractCalculateTool.prototype.config().table).bootstrapTable('destroy');
            TableInit(dataContractCalculateTool.prototype.config().table, "${pageContext.request.contextPath}/dataContractCalculateTool/list", cols, {}, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/dataContractCalculateTool/deleteDataContractCalculateToolById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除数据成功");
                            dataContractCalculateTool.prototype.loadDataDicList();
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
        },
        showModel: function () {
            $("#" + dataContractCalculateTool.prototype.config().frm).clearAll();
            dataContractCalculateTool.prototype.loadFiles(0);
            dataContractCalculateTool.prototype.showFileList(0);
            $('#' + dataContractCalculateTool.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + dataContractCalculateTool.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(dataContractCalculateTool.prototype.config().frm);
            $.ajax({
                url: "${pageContext.request.contextPath}/dataContractCalculateTool/saveAndUpdateDataContractCalculateTool",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        $('#' + dataContractCalculateTool.prototype.config().box).modal('hide');
                        dataContractCalculateTool.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/dataContractCalculateTool/getDataContractCalculateToolById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + dataContractCalculateTool.prototype.config().frm).clearAll();
                        $("#" + dataContractCalculateTool.prototype.config().frm).initForm(result.data);
                        dataContractCalculateTool.prototype.loadFiles(id);
                        dataContractCalculateTool.prototype.showFileList(id);
                        $('#' + dataContractCalculateTool.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        //显示附件列表
        showFileList: function (tableId) {
            FileUtils.getFileShows({
                target: "uploadFile",
                formData: {
                    tableName: AssessDBKey.DataContractCalculateTool,
                    tableId: tableId
                },
                signatureFlag:true,
                deleteFlag: true
            })
        },
        loadFiles: function (tableId) {
            FileUtils.uploadFiles({
                target: "uploadFile",
                onUpload: function (file) {
                    var formData = {
                        tableName: AssessDBKey.DataContractCalculateTool,
                        tableId: tableId
                    };
                    return formData;
                }, onUploadComplete: function (result, file) {
                    dataContractCalculateTool.prototype.showFileList(tableId);
                },
                deleteFlag: true
            });
        }

    }
</script>
<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">合同计算工具配置</h4>
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
                                                文号
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full " name="referenceNumber" placeholder="文号">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                客户名称
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full " name="customerName" placeholder="客户名称">
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
                                                <input id="uploadFile" placeholder="上传附件" class="form-control input-full" type="file">
                                                <div id="_uploadFile"></div>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="dataContractCalculateTool.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

</html>

