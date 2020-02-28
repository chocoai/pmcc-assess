<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <title>文件模板</title>
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
                                <div class="row">
                                    <div class="col-md-12">
                                        <form id="frmQuery" class="form-horizontal">
                                            <div class="form-group form-inline">
                                                <label class="col-md-1 col-form-label">名称</label>
                                                <div class="col-md-3 p-0">
                                                    <input type="text" data-rule-maxlength="50"
                                                           placeholder="名称" id="queryName" name="queryName"
                                                           class="form-control input-full">
                                                </div>
                                                <label class="col-md-1 col-form-label">备注</label>
                                                <div class="col-md-3 p-0">
                                                    <input type="text" data-rule-maxlength="50"
                                                           placeholder="备注" id="queryRemark" name="queryRemark"
                                                           class="form-control input-full">
                                                </div>
                                                <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                        onclick="loadDataList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                                    查询
                                                </button>
                                                <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                                        data-toggle="modal" onclick="addData()"
                                                        href="#divBox">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                                    新增
                                                </button>
                                            </div>


                                        </form>
                                        <table class="table table-bordered" id="tb_List">
                                            <!-- cerare document add ajax data-->
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>
<%--<%@include file="share/main_head.jsp" %>--%>
<!-- start: MAIN CONTAINER -->

</body>
<div id="divBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">附件模板管理</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frm" class="form-horizontal">
                    <input type="hidden" id="id" name="id" value="0">
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
                                                <input type="text" required data-rule-maxlength="50" placeholder="名称"
                                                       id="name" name="name" class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                备注
                                            </label>
                                            <div class="col-sm-11">
                                                <textarea placeholder="备注" class="form-control input-full" id="remark"
                                                          name="remark">
                                            </textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                附件
                                            </label>
                                            <div class="col-sm-11">
                                                <input id="uploadFile" type="file" multiple="false">
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
                <button type="button" class="btn btn-primary btn-sm" onclick="saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>


<%--
<div id="divBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">附件模板管理</h3>
            </div>
            <div class="modal-body">
                <form id="frm" class="form-horizontal">
                    <input type="hidden" id="id" name="id" value="0">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                名称<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-10">
                                <input type="text" required data-rule-maxlength="50" placeholder="名称"
                                       id="name" name="name" class="form-control input-full">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                备注
                            </label>
                            <div class="col-sm-10">
                                            <textarea placeholder="备注" class="form-control input-full" id="remark"
                                                      name="remark">
                                            </textarea>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                附件
                            </label>
                            <div class="col-sm-10">
                                <input id="uploadFile" type="file" multiple="false">
                                <div id="_uploadFile"></div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="saveData()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
--%>


<script type="application/javascript">

    $(function () {
        loadDataList();

        FileUtils.uploadFiles({
            target: "uploadFile",
            showFileList: false,
            onUpload: function (file) {//上传之前触发
                var formData = {
                    tableName: AssessDBKey.BaseFileTemplate,
                    creater: "${currUserAccount}",
                    tableId: $("#id").val()
                };
                return formData;
            },
            onUploadComplete: function () {
                loadTemplateFile($("#id").val());
            }
        });
    })

    //加载他项权利附件
    function loadTemplateFile(tableId) {
        FileUtils.getFileShows({
            target: "uploadFile",
            formData: {
                tableName: AssessDBKey.BaseFileTemplate,
                creater: "${currUserAccount}",
                tableId: tableId
            },
            deleteFlag: true
        });
    }


    //加载 价值时点 数据列表
    function loadDataList() {
        var cols = [];
        cols.push({field: 'name', title: '名称'});
        cols.push({field: 'remark', title: '备注'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<button onclick="editData(' + index + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button onclick="deleteData(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
                return str;
            }
        });
        $("#tb_List").bootstrapTable('destroy');
        TableInit("tb_List", "${pageContext.request.contextPath}/baseFileTemplate/getFileTemplateList", cols, {
            name: $("#queryName").val(),
            remark: $("#queryRemark").val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }

    //删除
    function deleteData(id, tbId) {
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/baseFileTemplate/deleteFileTemplate",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        notifySuccess("成功", "删除数据成功");
                        loadDataList();
                    }
                    else {
                        AlertError("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        })
    }

    //新增
    function addData() {
        $("#frm").clearAll();
        $("#id").val(0);
        loadTemplateFile(0);
    }

    //编辑
    function editData(index) {
        var row = $("#tb_List").bootstrapTable('getData')[index];
        $("#frm").clearAll();
        $("#frm").initForm(row);
        loadTemplateFile(row.id);
        $('#divBox').modal();
    }

    //保存
    function saveData() {
        var data = $("#frm").serialize();
        if ($("#frm").valid()) {
            $.ajax({
                url: "${pageContext.request.contextPath}/baseFileTemplate/saveFileTemplate",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        loadDataList();
                        $('#divBox').modal('hide');
                    }
                    else {
                        toastr.warning(result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        }
    }

    function adjustLiquidationAnalysis() {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/baseFileTemplate/adjustLiquidationAnalysis",
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    notifySuccess("成功",'调整成功');
                }
                else {
                    toastr.warning(result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        })
    }
</script>


</html>
