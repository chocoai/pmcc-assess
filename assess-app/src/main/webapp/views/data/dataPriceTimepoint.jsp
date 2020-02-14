<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body>
<%--<%@include file="share/main_head.jsp" %>--%>
<!-- start: MAIN CONTAINER -->
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
                                        <label for="queryName" class="col-md-1 col-form-label">价值时点名称</label>
                                        <div class="col-md-3 p-0">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="价值时点名称" id="queryName" name="queryName"
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
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>

</body>
<div id="divBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">价值时点说明管理</h4>
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
                                                <input type="text" required data-rule-maxlength="50"
                                                       placeholder="价值时点名称"
                                                       id="name" name="name" class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                类型<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="type"
                                                        class="form-control input-full search-select select2 type">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                类别<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="category"
                                                        class="form-control input-full category search-select select2">
                                                    <option selected="selected" value="">请先选择类型</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                描述
                                            </label>
                                            <div class="col-sm-10">
                                                 <textarea placeholder="描述" id="description" name="description"
                                                           class="form-control  input-full"></textarea>
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

<script type="application/javascript">

    $(function () {
        loadDataList();
        objMethod.event.init();
        objMethod.event.change();
    })

    //加载 价值时点 数据列表
    function loadDataList() {
        var cols = [];
        cols.push({field: 'name', title: '名称'});
        cols.push({field: 'description', title: '描述'});
        cols.push({field: 'categoryName', title: '类别'});
        cols.push({field: 'typeName', title: '类型'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑" onclick="editData(' + index + ');" ><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="deleteData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_List").bootstrapTable('destroy');
        TableInit("tb_List", "${pageContext.request.contextPath}/priceTimepoint/getPriceTimepointDescription", cols, {
            name: $("#queryName").val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }

    //删除 价值时点 数据
    function deleteData(id, tbId) {
        AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/priceTimepoint/deletePriceTimepointDescription",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        notifySuccess("成功", "删除数据成功");
                        loadDataList();//重载 (刷新)
                        $('#' + tbId).bootstrapTable("refresh");
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

    //对新增 价值时点 数据处理
    function addData() {
        $("#frm").clearAll();
    }

    //新增 价值时点 数据
    function saveData() {
        var flag = false;
        var data = $("#frm").serialize();
        if ($("#frm").valid()) {
            $.ajax({
                url: "${pageContext.request.contextPath}/priceTimepoint/addPriceTimepointDescription",
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

    //-------------------------------------------------------------------------------------

    function editData(index) {
        var row = $("#tb_List").bootstrapTable('getData')[index];
        $("#frm").clearAll();
        $("#frm").initForm(row);
        objMethod.event.init();
        $('#divBox').modal();
    }

    /**
     * @author:  zch
     * 描述:加载一些select2数据 (类型 类别)
     * @date:2018-08-30
     **/
    var objMethod = new Object();
    objMethod.flag = true;
    objMethod.isEmpty = function (data) {
        if (data) {
            return true;
        }
        return false;
    }
    objMethod.writeSelect = function (frm, data, name) {
        if (objMethod.isEmpty(data)) {
            $("#" + frm + " ." + name).val(data).trigger("change");
        } else {
            $("#" + frm + " ." + name).val(null).trigger("change");
        }
    }
    objMethod.event = {
        init: function () {
            if (objMethod.flag) {
                objMethod.event.type();
                objMethod.flag = false;
            }
        },
        //类型
        type: function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/baseProjectClassify/getProjectClassifyListByFieldName",
                type: "post",
                dataType: "json",
                data: {fieldName: "single"},//字段为固定 请参照BaseProjectClassifyController中....
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (data.length >= 1) {
                            var option = "<option value=''>请选择</option>";
                            for (var i = 0; i < data.length; i++) {
                                option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                            }
                            $("#frm").find('select.type');
                        }
                    }
                    else {
                        notifyWarning("获取类型失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    notifyWarning("调用服务端方法失败，失败原因:" + result);
                }
            });
        },
        change: function () {
            objMethod.event.category();
        },
        //类别
        category: function () {
            //监听change 事件 并做出......
            $("#frm" + " .type").change(function () {
                var pid = $("#frm" + " .type").eq(1).val();
                if (!objMethod.isEmpty(pid)) {
                    return false;
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/baseProjectClassify/getCacheProjectClassifyListByPid",
                    type: "post",
                    dataType: "json",
                    data: {pid: pid},
                    success: function (result) {
                        if (result.ret) {
                            var data = result.data;
                            if (data.length >= 1) {
                                var option = "<option value=''>请选择</option>";
                                for (var i = 0; i < data.length; i++) {
                                    option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                }
                                if ($("#frm").find('select.category').prev(".category").size() > 0) {
                                    $("#frm").find('select.category').prev(".category").remove();
                                }
                                $("#frm").find('select.category').html(html);
                            }
                        }
                        else {
                            notifyWarning("获取类别失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        notifyWarning("调用服务端方法失败，失败原因:" + result);
                    }
                })
            });
        }
    }
</script>


</html>
