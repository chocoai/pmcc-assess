<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<div id="div_Archives_box" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">新增档案</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 col-form-label">
                                            档案名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" required placeholder="档案名称" name="fileName"
                                                   class="form-control input-full">
                                        </div>
                                        <label class="col-sm-2 control-label">
                                            档案编号<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="档案编号" name="fileNumber" required
                                                   class="form-control input-full">
                                        </div>
                                    </div>
                                </div>
                            </div>




                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 col-form-label">
                                            档案类型<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <select name="fileType" required
                                                    class="form-control input-full search-select select2">
                                                <option value="">请选择</option>
                                                <c:forEach items="${ArchivesFileTypeList}" var="ArchivesFileType">
                                                    <option value="${ArchivesFileType.key}">${ArchivesFileType.value}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-sm-2">
                                        </div>
                                        <div class="col-sm-4">
                                            <div class="form-check" style="justify-content:left">
                                                <label class="form-check-label">
                                                    <input class="form-check-input" type="checkbox"
                                                           name="bisPaper" value="true">
                                                    <span class="form-check-sign">存在纸质文件</span>
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 col-form-label">
                                            年份
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="年份" name="year" data-rule-number='true'
                                                   class="form-control input-full">
                                        </div>
                                        <label class="col-sm-2 control-label">
                                            排序
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="排序" name="fileSort" data-rule-number='true'
                                                   class="form-control input-full">
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
                <button type="button" class="btn btn-primary btn-sm" onclick="objArchives.saveArchives()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <%@include file="/views/share/project/projectInfoSimple.jsp" %>
                    <%@include file="/views/share/project/projectPlanDetails.jsp" %>

                    <!-- 填写表单 start -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        项目归档
                                    </div>
                                    <div class="card-tools">
                                        <button type="button" class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="card-body">
                                <form class="form-horizontal">
                                    <input type="hidden" name="projectId" value="${projectInfo.id}">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline">
                                                <label class="col-sm-1 control-label">
                                                    档案名称
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-maxlength="50" placeholder="档案名称"
                                                           name="fileName"
                                                           class="form-control input-full">
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    档案编号
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-maxlength="50" placeholder="档案编号"
                                                           name="fileNumber"
                                                           class="form-control input-full">
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    年份
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-maxlength="50" placeholder="年份"
                                                           name="year"
                                                           class="form-control input-full">
                                                </div>
                                                <div class="col-sm-3">
                                                    <button type="button" class="btn btn-info btn-sm"
                                                            onclick="objArchives.loadTableList(this);">
                                                        <span class="btn-label"><i class="fa fa-search"></i></span>
                                                        查询
                                                    </button>
                                                    <button class="btn btn-success btn-sm" style="margin-left: 5px;"
                                                            type="button"
                                                            onclick="objArchives.appendArchives(this) ;">
                                                        <span class="btn-label"><i
                                                                class="fa fa-plus"></i></span>添加
                                                    </button>
                                                    <button type="button" class="btn btn-primary btn-sm"
                                                            onclick="objArchives.autoCreateProjectFileCompleteNow(this);">
                                                        <span class="btn-label"><i class="fab fa-autoprefixer"></i></span>
                                                        自动创建档案
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <table class="table table-bordered" id="tb_Archives_list"></table>
                                </form>
                            </div>
                        </div>
                    </div>


                    <%@include file="/views/share/form_apply.jsp" %>
                    <%@include file="/views/share/form_log.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>


<script type="text/javascript">

    var objArchives = {};

    objArchives.table = $("#tb_Archives_list");
    objArchives.box = $("#div_Archives_box");

    objArchives.loadTableList = function (_this) {
        var data = {projectId: '${projectInfo.id}'};
        if (_this) {
            data = formSerializeArray($(_this).closest("form"));
        }
        var arr = Object.keys(data);
        $.each(arr, function (i, item) {
            if (!data[item]) {
                data[item] = undefined;
            }
        });
        var table = $(objArchives.table.selector);
        var cols = [];
        cols.push({field: 'year', title: '年份', width: "10%"});
        cols.push({field: 'fileTypeName', title: '档案类型', width: "10%"});
        cols.push({field: 'fileNumber', title: '档案编号', width: "10%"});
        cols.push({field: 'fileName', title: '档案名称', width: "10%"});
        cols.push({
            field: 'id', title: '文档',width: "15%",formatter: function (value, row) {
                var show = '<div id="_project_proxy' + row.id + '"></div>';
                FileUtils.getFileShows({
                    target: "project_proxy" + row.id,
                    formData: {
                        tableName: 'tb_project_file_complete',
                        tableId: row.id,
                        fieldsName: "project_proxy" + row.id
                    },
                    showMode: 'table',
                    deleteFlag: false
                });
                return show;
            }
        });
        cols.push({
            field: 'id', title: '操作', width: "25%", formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<button type="button" onclick="objArchives.editDataDic(' + row.id + ')"  style="margin-left: 5px;"  class="btn   btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';
                str += '<button type="button" onclick="objArchives.delDataDic(' + row.id + ')"  style="margin-left: 5px;"  class="btn   btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
                return str;
            }
        });
        table.bootstrapTable('destroy');
        TableInit(table, "${pageContext.request.contextPath}/projectFileComplete/getProjectFileCompleteList", cols, data, {
            method: "get",
            showColumns: false,
            showRefresh: false,
            search: false,
            striped: true,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        });
    };

    objArchives.appendArchives = function () {
        var box = $(objArchives.box.selector);
        box.modal("show");
        var frm = box.find("form");
        frm.clearAll();
        frm.validate();
    };

    objArchives.delDataDic = function(id) {
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/projectFileComplete/deleteProjectFileCompleteById",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        notifySuccess("成功", "删除数据成功");
                        objArchives.loadTableList() ;
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
    } ;


    objArchives.autoCreateProjectFileCompleteNow = function() {
        AlertConfirm("是否自动创建档案", "自动创建档案会清除之前的档案数据", function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/projectFileComplete/autoCreateProjectFileCompleteNow",
                type: "post",
                dataType: "json",
                data: {projectId: '${projectInfo.id}' },
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        notifySuccess("成功", "创建档案成功");
                        objArchives.loadTableList() ;
                    }
                    else {
                        AlertError("失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        })
    } ;

    objArchives.editDataDic = function(id) {
        objArchives.appendArchives() ;
        var frm = $(objArchives.box.selector).find("form");
        var table = $(objArchives.table.selector);
        var data = table.bootstrapTable('getRowByUniqueId', id);
        frm.initForm(data);
    } ;

    objArchives.saveArchives = function () {
        var box = $(objArchives.box.selector);
        var frm = box.find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        data.projectId = '${projectInfo.id}' ;
        objArchives.getFileNumberCount(data, function (count) {
            count = Number(count);
            if (count >= 1) {
                notifyInfo("提示", "档案编号已经存在");
                return false;
            }
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/projectFileComplete/saveProjectFileComplete",
                type: "post",
                dataType: "json",
                data: {formData: JSON.stringify(data)},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        objArchives.loadTableList() ;
                        box.modal("hide");
                    } else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        });
    };

    objArchives.getFileNumberCount = function (data, callback) {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectFileComplete/getFileNumberCount",
            type: "get",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    AlertError("获取数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        })
    };


    $(document).ready(function () {
        objArchives.loadTableList();
    });

</script>
<script type="application/javascript">
    //提交
    function submit() {
        var data = {};

        if ("${processInsId}" != "0") {
            submitEditToServer(JSON.stringify(data));
        } else {
            submitToServer(JSON.stringify(data));
        }
    }


</script>

</html>

