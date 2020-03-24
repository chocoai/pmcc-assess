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
                                        <label for="queryName" class="col-md-1 col-form-label">项目类型</label>
                                        <div class="col-md-3 p-0">
                                            <select class="form-control input-full" required id="queryType">
                                                <option value="">--请选择--</option>
                                                <c:forEach var="classItem" items="${keyValueDtoList}">
                                                    <c:forEach var="typeItem" items="${classItem.keyValueDtos}">
                                                        <c:if test="${not empty typeItem.keyValueDtos}">
                                                            <c:forEach var="categoryItem"
                                                                       items="${typeItem.keyValueDtos}">
                                                                <option value="${categoryItem.key}">${categoryItem.value}</option>
                                                            </c:forEach>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <label for="queryName" class="col-md-1 col-form-label">名称</label>
                                        <div class="col-md-3 p-0">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="名称" id="queryName" name="queryName"
                                                   class="form-control input-full">
                                        </div>
                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="projectXlxPigeonholeConfig.prototype.loadDataDicList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                        <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                                data-toggle="modal"
                                                onclick="projectXlxPigeonholeConfig.prototype.showModel()"
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
<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">归档信息</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmFather" class="form-horizontal">
                    <input type="hidden" id="id" name="id" value="0">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                项目类型<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <select class="form-control input-full" required name="projectType">
                                                    <option value="">--请选择--</option>
                                                    <c:forEach var="classItem" items="${keyValueDtoList}">
                                                        <c:forEach var="typeItem" items="${classItem.keyValueDtos}">
                                                            <c:if test="${not empty typeItem.keyValueDtos}">
                                                                <c:forEach var="categoryItem"
                                                                           items="${typeItem.keyValueDtos}">
                                                                    <option value="${categoryItem.key}">${categoryItem.value}</option>
                                                                </c:forEach>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:forEach>
                                                </select></div>
                                            <label class="col-sm-2 col-form-label">
                                                文件名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" required data-rule-maxlength="50" placeholder="文件名称"
                                                       id="fileName" name="fileName" class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                排序
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" data-rule-number='true' placeholder="排序"
                                                       id="sorting" name="sorting" class="form-control input-full">
                                            </div>
                                            <div class="col-sm-6">
                                                <div class="form-check" style="justify-content:left">
                                                    <label class="form-check-label">
                                                        <input class="form-check-input" type="checkbox" id="bisEnable"
                                                               name="bisEnable" value="true"
                                                               checked="checked">
                                                        <span class="form-check-sign">是否启用</span>
                                                    </label>
                                                </div>
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
                <button type="button" class="btn btn-primary btn-sm"
                        onclick="projectXlxPigeonholeConfig.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        projectXlxPigeonholeConfig.prototype.loadDataDicList();
    });
    var projectXlxPigeonholeConfig = function () {

    };
    projectXlxPigeonholeConfig.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_FatherList";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            return data;
        },
        loadDataDicList: function () {
            var cols = [];
            cols.push({field: 'typeName', width: '30%', title: '项目类型'});
            cols.push({field: 'fileName', width: '30%', title: '文件名称'});
            cols.push({field: 'sorting', width: '15%', title: '排序'});
            cols.push({
                field: 'bisEnable', width: '15%', title: '启用', formatter: function (value, row, index) {
                    if(true == value) {
                        return "是";
                    }else{
                        return "否";
                    }
                }
            });
            cols.push({
                field: 'id', width: '15%', title: '操作', formatter: function (value, row, index) {
                    var str = '<button onclick="projectXlxPigeonholeConfig.prototype.getAndInit(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                    str += '<i class="fa fa-pen"></i>';
                    str += '</button>';
                    str += '<button onclick="projectXlxPigeonholeConfig.prototype.removeData(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                    str += '<i class="fa fa-minus"></i>';
                    str += '</button>';
                    return str;
                }
            });
            $("#" + projectXlxPigeonholeConfig.prototype.config().table).bootstrapTable('destroy');
            TableInit(projectXlxPigeonholeConfig.prototype.config().table, "${pageContext.request.contextPath}/projectXlxPigeonholeConfig/list", cols, {
                queryName: $("#queryName").val(),
                queryType: $("#queryType").val()
            }, {
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
                    url: "${pageContext.request.contextPath}/projectXlxPigeonholeConfig/delete",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除数据成功");
                            projectXlxPigeonholeConfig.prototype.loadDataDicList();
                        } else {
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
            $("#" + projectXlxPigeonholeConfig.prototype.config().frm).clearAll();
            $('#' + projectXlxPigeonholeConfig.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + projectXlxPigeonholeConfig.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(projectXlxPigeonholeConfig.prototype.config().frm);
            data.bisEnable = $('#bisEnable').prop('checked');
            $.ajax({
                url: "${pageContext.request.contextPath}/projectXlxPigeonholeConfig/save",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        $('#' + projectXlxPigeonholeConfig.prototype.config().box).modal('hide');
                        projectXlxPigeonholeConfig.prototype.loadDataDicList();
                    } else {
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
                url: "${pageContext.request.contextPath}/projectXlxPigeonholeConfig/get",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + projectXlxPigeonholeConfig.prototype.config().frm).clearAll();
                        $("#" + projectXlxPigeonholeConfig.prototype.config().frm).initForm(result.data);
                        $('#' + projectXlxPigeonholeConfig.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        }

    }
</script>


</html>
