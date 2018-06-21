<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed"><%--<%@include file="share/main_head.jsp" %>--%><!-- start: MAIN CONTAINER -->
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="row">
                <div class="col-md-12 ">
                    <!-- start: DEFAULT TREE PANEL -->

                    <div class="x_panel">
                        <div class="x_title collapse-link">
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                            </ul>
                            <h2>${baseViewDto.currentMenu.name}</h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <p id="toolbar">
                                <button type="button" class="btn btn-success"
                                        onclick="addForm()"> 新增
                                </button>
                            </p>
                            <table id="tb_reportTable" class="table table-bordered">

                            </table>
                        </div>
                    </div>
                    <!-- end: DEFAULT TREE PANEL -->
                </div>
            </div>
        </div>
    </div>
</div>


<div id="divForm" class="modal fade bs-example-modal-sm" data-backdrop="static" aria-hidden="true"
     role="dialog" data-keyboard="false" tabindex="1" style="display: none;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">编辑表或视图</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="frm_form">
                    <div class="form-group ">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                显示名称
                            </label>
                            <div class="col-sm-9">
                                <input type="hidden" id="id" name="id">
                                <input type="hidden" id="menuId" name="menuId">
                                <input type="text" required placeholder="显示名称" id="tableCnName" name="tableCnName"
                                       class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="form-group ">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                表名视图名称
                            </label>
                            <div class="col-sm-9">

                                <select required id="tableName" name="tableName" class="form-control search-select select2">
                                    <option value="">请选择</option>
                                    <c:forEach var="item" items="${tableInfo}">
                                        <option value="${item.tableName}">${item.tableComment}(${item.tableName})</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group ">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                表单类型
                            </label>
                            <div class="col-sm-9">
                                <select required id="reportType" name="reportType" class="form-control">
                                    <option value="1">基础数据表单</option>
                                    <option value="2">报表</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group ">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                允许导出
                            </label>
                            <div class="col-sm-4">
                                <select required id="bisExport" name="bisExport" class="form-control">
                                    <option value="1" selected="selected">是</option>
                                    <option value="0">否</option>
                                </select>
                            </div>
                        </div>
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                数据权限控制
                            </label>
                            <div class="col-sm-3">
                                <select required id="bisBaseRole" name="bisBaseRole" class="form-control">
                                    <option value="1">是</option>
                                    <option value="0">否</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group ">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                所属菜单
                            </label>
                            <div class="col-sm-9">
                                <select required id="menuPid" name="menuPid" class="form-control">
                                    <option value="">请选择</option>
                                    <c:forEach var="item" items="${sysMenus}">
                                        <option value="${item.id}">${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="SaveSetForm()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
<div id="divFormColumns" class="modal fade bs-example-modal-sm" data-backdrop="static" aria-hidden="true"
     role="dialog" data-keyboard="false" tabindex="1" style="display: none;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">字段设置</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal">
                    <input type="hidden" name="tableId" id="tableId">
                    <input type="hidden" name="columnsCount" id="columnsCount">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="hidden-xs">字段名称</th>
                            <th class="hidden-xs">显示名称</th>
                            <th class="hidden-xs">字段类型</th>
                            <th class="hidden-xs">是否显示</th>
                            <th class="hidden-xs">是否查询</th>
                            <th class="hidden-xs">是否必填</th>
                            <th class="hidden-xs">数据源</th>
                            <th class="hidden-xs">排序</th>
                        </tr>
                        </thead>
                        <tbody id="tb_body">

                        </tbody>
                    </table>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="SaveFormColumns()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">


    $(function () {
        var cols = [];
        cols.push({field: 'id', title: '编号'});
        cols.push({field: 'tableCnName', title: '显示名称'});
        cols.push({field: 'tableName', title: '表名视图名称'});
        cols.push({field: 'reportType', title: '表单类型'});
        cols.push({
            field: 'bisExport', title: '允许导出', formatter: function (value) {
                return getBoolChs(value);
            }
        });
        cols.push({
            field: 'bisBaseRole', title: '数据权限控制', formatter: function (value) {
                return getBoolChs(value);
            }
        });
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success" href="javascript:loadTableColumns(' + row.id + ',\'' + row.tableName + '\')"><i class="fa fa-edit"></i>字段设置</a>';
                str += '<a class="btn btn-xs btn-success" href="javascript:editForm(' + row.id + ');" ><i class="fa fa-edit">编辑</i></a>';
                str += '<a class="btn btn-xs btn-warning" href="javascript:delForm(' + row.id + ')"><i class="fa fa-trash-o"></i>删除</a>';
                str += '</div>';
                return str;
            }
        });
        TableInit("tb_reportTable", "${pageContext.request.contextPath}/reportSet/getReportTableList", cols);
    });

    function editForm(id) {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/reportSet/getReportTableById",
            data: {
                id: id
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    $("#frm_form").initForm(result.data);
                    if (result.data["bisExport"]) {
                        $("#bisExport").val("1");
                    }
                    else {
                        $("#bisExport").val("0");
                    }
                    if (result.data["bisExport"]) {
                        $("#bisBaseRole").val("1");
                    }
                    else {
                        $("#bisBaseRole").val("0");
                    }
                    $("#tableName").select2().val(result.data["tableName"]).trigger("change");
                    $('#divForm').modal({backdrop: 'static', keyboard: false});
                }
                else {
                    Alert("导入表结构失败，失败原因：" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });


    }
    function delForm(id) {
        Alert("删除表单后将不能恢复，是否确认删除" + "?", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/reportSet/deleteReportTableList",
                data: {
                    id: id
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success("删除表单成功");
                        TableReload("tb_reportTable");
                    }
                    else {
                        Alert("删除数据失败，失败原因：" + result.errmsg, 1, null, null);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                }
            });
        });
    }
    function addForm() {
        $("#frm_form").clearAll();
        $("#id").val(0);
        $('#divForm').modal({backdrop: 'static', keyboard: false});
    }
    function SaveSetForm() {
        //写入数据到数据库
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/reportSet/saveReportTableList",
            data: formParams("frm_form"),
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success("创建表单成功");
                    $('#divForm').modal('hide');
                    TableReload("tb_reportTable");
                }
                else {
                    Alert("保存数据失败，失败原因：" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }

    function loadTableColumns(id, tableName) {
        //写入数据到数据库
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/reportSet/getTableColumns",
            data: {
                tableId: id,
                tableName: tableName
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                var html = "";
                $.each(result.data, function (i, item) {
                    html += "<tr>";
                    html += "<td class='hidden-xs'>";
                    html += "<input type='hidden' id='columnsName_" + i + "' name='columnsName_" + i + "' value='" + item.columnsName + "'>" + item.columnsName;
                    html += "</td>";
                    html += "<td class='hidden-xs'>";
                    html += "<input type='text' id='columnsCnName_" + i + "' name='columnsCnName_" + i + "' value='" + item.columnsCnName + "' class='form-control'>";
                    html += "</td>";
                    html += "<td class='hidden-xs'>";
                    html += "<select type='text' id='typeId_" + i + "' name='typeId_" + i + "' class='form-control'>";
                    html += "${typeList}";
                    html += "</select>";
                    html += "</td>";

                    html += "<td class='hidden-xs'>";
                    if (item.bisShow) {
                        html += "<input type='checkbox' id='bisShow_" + i + "' name='bisShow_" + i + "' checked='checked' >";
                    }
                    else {
                        html += "<input type='checkbox' id='bisShow_" + i + "' name='bisShow_" + i + "' >";
                    }
                    html += "</td>";
                    html += "<td class='hidden-xs'>";
                    if (item.bisSelect) {
                        html += "<input type='checkbox' id='bisSelect_" + i + "' name='bisSelect_" + i + "' checked='checked' >";
                    }
                    else {
                        html += "<input type='checkbox' id='bisSelect_" + i + "' name='bisSelect_" + i + "'>";
                    }
                    html += "</td>";
                    html += "<td class='hidden-xs'>";
                    if (item.bisMust) {
                        html += "<input type='checkbox' id='bisMust_" + i + "' name='bisMust_" + i + "' checked='checked' >";
                    }
                    else {
                        html += "<input type='checkbox' id='bisMust_" + i + "' name='bisMust_" + i + "'>";
                    }
                    html += "</td>";
                    html += "<td class='hidden-xs'>";
                    html += "<input type='text' id='selectSource_" + i + "' name='selectSource_" + i + "' value='" + item.selectSource + "' class='form-control'>";
                    html += "</td>";
                    html += "<td class='hidden-xs'>";
                    html += "<input type='text' id='sorting_" + i + "' name='sorting_" + i + "' value='" + item.sorting + "' class='form-control'>";
                    html += "</td>";
                    html += "</tr>";
                });
                $("#tb_body").html(html);
                $.each(result.data, function (i, item) {
                    $("#typeId_" + i).val(item.typeId);
                });
                $("#tableId").val(id);
                $("#columnsCount").val(result.data.length);
                $('#divFormColumns').modal({backdrop: 'static', keyboard: false});
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }

    function SaveFormColumns() {
        //写入数据到数据库
        Loading.progressShow();
        var subData = [];
        for (var i = 0; i < $("#columnsCount").val(); i++) {
            var data = {
                tableId: $("#tableId").val(),
                columnsName: $("#columnsName_" + i).val(),
                columnsCnName: $("#columnsCnName_" + i).val(),
                typeId: $("#typeId_" + i).val(),
                bisSelect: $("#bisSelect_" + i).is(':checked') ? "1" : "0",
                bisShow: $("#bisShow_" + i).is(':checked') ? "1" : "0",
                bisMust: $("#bisMust_" + i).is(':checked') ? "1" : "0",
                selectSource: $("#selectSource_" + i).val(),
                sorting: $("#sorting_" + i).val()
            };
            subData.push(data);
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/reportSet/saveReportColumnList",
            data: {
                ds: JSON.stringify(subData),
                tableId: $("#tableId").val()
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success("字段设置成功");
                    $('#divFormColumns').modal('hide');
                }
                else {
                    Alert("保存数据失败，失败原因：" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }

</script>

<script type="application/javascript">

    $(function () {
        $("#projectClassId").change(function () {
            $("#projectTypeId,#projectCategoryId").empty();

            loadDataDicByPid($(this).val(), function (retHtml, data) {
                $("#projectTypeId").html(retHtml);
            });
        })
        $("#projectTypeId").change(function () {
            loadDataDicByPid($(this).val(), function (retHtml, data) {
                $("#projectCategoryId").html(retHtml);
            });
        })

        $("#btnAddObject").click(function () {
            $("#projectTypeId,#projectCategoryId").empty();
        })
        setTimeout(function () {
            $(".btn-edit-object").on("click", document, function () {
                alert(1);
                setTimeout(initTypeAndCategory(), 300);
            })
        }, 2000);
        $("#tableName").select2();
    })

    function initTypeAndCategory() {
        alert(2);
        if (editInfo) {
            alert(3);
            var projectClassId = "";
            var projectTypeId = "";
            var projectCategoryId = "";
            $.each(editInfo, function (i, item) {
                if (item.columnsName === "projectClassId") {
                    projectClassId = item.columnsValue;
                }
                if (item.columnsName === "projectTypeId") {
                    projectTypeId = item.columnsValue;
                }
                if (item.columnsName === "projectCategoryId") {
                    projectCategoryId = item.columnsValue;
                }
            });
            loadDataDicByPidExtend(projectClassId, projectTypeId, function (retHtml, data) {
                $("#projectTypeId").html(retHtml);
            });
            loadDataDicByPidExtend(projectTypeId, projectCategoryId, function (retHtml, data) {
                $("#projectCategoryId").html(retHtml);
            });
        } else {
            setTimeout(initTypeAndCategory(), 300);
        }
    }
</script>
</body>
</html>
