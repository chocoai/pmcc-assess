<%--
  Created by IntelliJ IDEA.
  User: red
  Date: 2017/10/17
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>报告模板管理</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>


<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="row">
                <div class="col-xs-12">
                    <div class="x_panel">
                        <div class="x_title">
                            <h2>
                                <i class="fa ${baseViewDto.currentMenu.icon}"></i>
                                ${baseViewDto.currentMenu.name}
                            </h2>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <div id="tree" class="col-md-3">

                            </div>

                            <div class="col-md-9">
                                <div class="x_content">
                                    <input type="hidden" id="tabs_index_value">
                                    <input type="hidden" id="tree_value" value="0">
                                    <div class="col-xs-10">
                                        <!-- Tab panes -->
                                        <div class="tab-content">
                                            <div class="tab-pane" id="settings">
                                                <label class="label label-warning" id="lab_entrust"></label>
                                                <c:forEach var="reportItem" items="${reportType}">
                                                    <label class="radio-inline"><input type="radio" value="${reportItem.id}" name="reportType" checked="checked"
                                                                                       onclick="reloadTableList()"> ${reportItem.name}</label>
                                                </c:forEach>
                                                <p id="toolbar">
                                                    <a class="btn btn-success" onclick="addBookmark(0)">
                                                        新增
                                                    </a>
                                                </p>
                                                <table id="tb_fileds_list" class="table table-bordered"></table>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-2">
                                        <!-- required for floating -->
                                        <!-- Nav tabs -->
                                        <ul class="nav nav-tabs tabs-right">
                                            <c:forEach var="item" items="${entrust}">
                                                <li id="settings-li-${item.id}" class=""><a id="tabs_a_${item.id}" href="#settings" onclick="changeTabs(${item.id})" data-toggle="tab"
                                                                                            aria-expanded="false">${item.name}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
</html>
<div id="modalSubTemplate" class="modal fade bs-example-modal-lg"
     data-backdrop="static" tabindex="1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">

        <div class="modal-content">
            <div class='modal-header'>
                <h3 class='modal-title'>报告模板</h3>
            </div>
            <p id="sublevel_toolbar">
                <a class="btn btn-success" onclick="addBookmark(1)">
                    新增
                </a>
            </p>
            <table id="tb_fileds_sublevel_list" class="table table-bordered"></table>
            <div class='modal-footer'>
                <button type='button' data-dismiss='modal' class='btn btn-default'>取消</button>
            </div>
        </div>

    </div>
</div>


<div id="modalTemplate" class="modal fade bs-example-modal-lg"
     data-backdrop="static" tabindex="1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class='modal-header'>
                <h3 class='modal-title'>报告模板</h3></div>
            <form id='frm' class='form-horizontal'>
                <input type='hidden' id='id' name='id' value="0">
                <input type='hidden' id='pid' name='pid' value="0">
                <div class='modal-body'>
                    <div class='row'>
                        <div class='col-md-12'>
                            <div class='panel-body'>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            添加类型<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select id='templateType' name='templateType' required class='form-control'>
                                                <c:forEach var="item" items="${baseReportTemplateTypeEnumList}">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            书签/子模板名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input name='bookmarkName' class='form-control' required
                                                   maxlength="200">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class='x-valid'><label
                                            class='col-sm-2 control-label'>数据来源<span
                                            class="symbol required"></span></label>
                                        <div class='col-sm-10'>
                                            <select id='dataPoolType' name='dataPoolType' required class='form-control' onclick="changePool()">
                                                <c:forEach var="item" items="${baseReportDataPoolTypeEnumList}">
                                                    <option value="${item.key}">${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class='x-valid'>
                                        <label
                                                class='col-sm-2 control-label'></label>
                                        <div class='col-sm-5' id="div_dataPoolTableId" style="display: none;">
                                            <select id='dataPoolTableId' name='dataPoolTableId' onclick="loadTableCloumns()" class='form-control  search-select select2'>
                                                <option value="">--请选择--</option>
                                            </select>
                                        </div>
                                        <div class='col-sm-5' id="div_dataPoolColumnsId" style="display: none;">
                                            <select id='dataPoolColumnsId' name='dataPoolColumnsId' class='form-control  search-select select2'>
                                                <option value="">--请选择--</option>
                                            </select>
                                        </div>
                                        <div class='col-sm-10' id="div_dataPoolTemplateId" style="display: none;">
                                            <select id='dataPoolTemplateId' name='dataPoolTemplateId' class='form-control  search-select select2'>
                                                <option value="">--请选择--</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class='modal-footer'>
                <button type='button' data-dismiss='modal' class='btn btn-default'>取消</button>
                <button type='button' class='btn btn-primary save_custom_model' onclick="saveTemplate();"
                        id='btn_save_bid_organization_agency'>保存
                </button>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    var tableList = $("#tb_fileds_list");
    var tableSubList = $("#tb_fileds_sublevel_list");
    $(function () {
        loadTree();
        $("#settings").addClass("active");
        $("#settings-li-${firstEntrust}").addClass("active");
        $(":radio[name='reportType'][value='${firstReportType}']").prop("checked", "checked");
        loadDatagrid();
        loadSubDatagrid();
        changeTabs(${firstEntrust});
        $("#dataPoolTableId").select2();
        $("#dataPoolColumnsId").select2();
        $("#dataPoolTemplateId").select2();
    });
    function changeTabs(id) {
        $("#lab_entrust").html($("#tabs_a_" + id + "").html());
        $("#tabs_index_value").val(id);
        reloadTableList();
    }
    function reloadTableList() {
        TableReload(tableList, "${pageContext.request.contextPath}/templateSet/getBaseReportTemplateList", {
            customId: $("#tree_value").val(),
            entrustId: $("#tabs_index_value").val(),
            reportId: $("input[name='reportType']:checked").val()
        });
    }

    function reloadSubTableList() {
        TableReload(tableSubList, "${pageContext.request.contextPath}/templateSet/getBaseReportSubTemplateList", {
            customId: $("#tree_value").val(),
            entrustId: $("#tabs_index_value").val(),
            reportId: $("input[name='reportType']:checked").val(),
            pid: $("#pid").val()
        });
    }
    function loadTree() {

        initBaseTreeView("tree", "${pageContext.request.contextPath}/templateSet/queryCustomerTree", {pid: -1}, false, function (objs) {
            treeView_setValue("tree", 0);
            objs.on('nodeSelected', function (event, node) {
                if (node.id >= 0) {
                    $("#tree_value").val(node.id);
                    reloadTableList();
                }
            });
        });
    }
    function loadSubDatagrid() {

        var cols = [];
        cols.push({field: 'id', title: '编号', visible: false});
        cols.push({
            field: 'bookmarkName', title: '书签名称', width: '50%', formatter: function (value, row, index) {
                var s = value;
                if (row.templateType == "${templateTypeId}") {
                    s = "<a href='javascript:;' class='btn btn-xs btn-danger tooltips'  data-toggle='tooltip' data-original-title='模板' style='margin-left: 5px'><i  class='fa fa-tag' title='模板'></i></a>" + value;
                }
                return s;
            }
        });
        cols.push({field: 'typeName', width: '10%', title: '字段类型'});
        cols.push({field: 'dataPoolTypename', width: '20%', title: '数据来源', formatter: function (value, row, index) {
            var s = value;
            if (row.templateType == "${templateId}") {
                s = "<a href='javascript:;' onclick='showSubTemplate("+row.dataPoolTemplateId+")'>" + value + "</a>"
            }
            return s;
        }});
        cols.push({
            field: 'opation', title: '操作', width: '15%', formatter: function (value, row, index) {
                var s = "<a href='javascript:;' class='btn btn-xs btn-success tooltips'  data-toggle='tooltip' data-original-title='编辑'  data-toggle='modal' onclick='editBookmark(" + row.id + ")' style='margin-left: 5px'><i  class='fa fa-edit fa-white' title='编辑'></i></a>";
                s += "<a href='javascript:;' class='btn btn-xs btn-warning tooltips'  data-toggle='tooltip' data-original-title='删除' onclick='deleteBookmark("+row.id+")' style='margin-left: 5px'><i class='fa fa-minus fa-white' title='删除'></i></a>";
                return s;
            }
        });

        TableInit(tableSubList, "${pageContext.request.contextPath}/templateSet/getBaseReportTemplateList", cols,
            {
                customId: $("#tree_value").val(),
                entrustId: $("#tabs_index_value").val(),
                reportId: $("input[name='reportType']:checked").val(),
                pid: $("#pid").val()
            }, {
                toolbar: '#sublevel_toolbar',
                onLoadSuccess: function () {
                    $(".tooltips").tooltip();
                }
            });
    }
    function loadDatagrid() {

        var cols = [];
        cols.push({field: 'id', title: '编号', visible: false});
        cols.push({
            field: 'bookmarkName', title: '书签名称', width: '50%', formatter: function (value, row, index) {
                var s = value;
                if (row.templateType == "${templateTypeId}") {
                    s = "<a href='javascript:;' onclick='showSubTemplate(" + row.id + ")' class='btn btn-xs btn-danger tooltips'  data-toggle='tooltip' data-original-title='模板' style='margin-left: 5px'><i  class='fa fa-tag' title='模板'></i></a>" + value;
                }
                return s;
            }
        });
        cols.push({field: 'typeName', width: '10%', title: '字段类型'});
        cols.push({
            field: 'dataPoolTypename', width: '20%', title: '数据来源', formatter: function (value, row, index) {
                var s = value;
                if (row.dataPoolType == "${templateId}") {
                    s = "<a href='javascript:;' onclick='showSubTemplate("+row.dataPoolTemplateId+")'>" + value + "</a>"
                }
                return s;
            }
        });
        cols.push({
            field: 'opation', title: '操作', width: '15%', formatter: function (value, row, index) {
                var s = "<a href='javascript:;' class='btn btn-xs btn-success tooltips'  data-toggle='tooltip' data-original-title='编辑'  data-toggle='modal' onclick='editBookmark(" + row.id + ")' style='margin-left: 5px'><i  class='fa fa-edit fa-white' title='编辑'></i></a>";
                s += "<a href='javascript:;' class='btn btn-xs btn-warning tooltips'  data-toggle='tooltip' data-original-title='删除' onclick='deleteBookmark("+row.id+")' style='margin-left: 5px'><i class='fa fa-minus fa-white' title='删除'></i></a>";
                return s;
            }
        });

        TableInit(tableList, "${pageContext.request.contextPath}/templateSet/getBaseReportTemplateList", cols,
            {
                customId: $("#tree_value").val(),
                entrustId: $("#tabs_index_value").val(),
                reportId: $("input[name='reportType']:checked").val()
            }, {
                onLoadSuccess: function () {
                    $(".tooltips").tooltip();
                }
            });
    }


    function addBookmark(id) {
        var pid = $("#pid").val();
        $("#frm").clearAll();
        $("#id").val(0);
        $("#dataPoolTableId").hide();
        $("#dataPoolColumnsId").hide();
        $("#dataPoolTemplateId").hide();
        if (id == 0) {
            $("#pid").val(0);
        }
        else {
            $("#pid").val(pid);
        }
        $('#modalTemplate').modal({backdrop: 'static', keyboard: false});
    }

    function editBookmark(id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/templateSet/getBaseReportTemplateById",
            data: {
                id: id
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                   var data=result.data;
                    $("#frm").clearAll();
                    $("#frm").initForm(data);
                    $("#dataPoolTableId").select2().val(data.dataPoolTableId).trigger("change");
                    $("#dataPoolColumnsId").select2().val(data.dataPoolColumnsId).trigger("change");
                    $("#dataPoolTemplateId").select2().val(data.dataPoolTemplateId).trigger("change");
                    $("#dataPoolType").val(data.dataPoolType);
                    changePool();
                    loadTableCloumns();
                    $('#modalTemplate').modal({backdrop: 'static', keyboard: false});
                }
                else {
                    Alert("取数失败，失败原因：" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }

    function changePool() {
        var dataPoolType = $("#dataPoolType").val();
        if (!dataPoolType) {
            return false;
        }
        if (dataPoolType != "${templateId}") {
            $("#div_dataPoolTableId").show();
            $("#div_dataPoolColumnsId").show();
            $("#div_dataPoolTemplateId").hide();
        }
        else {
            $("#div_dataPoolTableId").hide();
            $("#div_dataPoolColumnsId").hide();
            $("#div_dataPoolTemplateId").show();
        }


        $.ajax({
            url: "${pageContext.request.contextPath}/templateSet/getBaseDataPool",
            type: "get",
            dataType: "json",
            data: {
                typeId: dataPoolType,
                customerId: $("#tree_value").val()
            },
            success: function (result) {
                var retHtml = '<option value="" selected>-请选择-</option>';
                if (result.ret) {

                    $.each(result.data, function (i, item) {
                        retHtml += ' <option value="' + item.key + '">' + item.value + '</option>';
                    });
                }
                if (dataPoolType != "${templateId}") {
                    $("#dataPoolTableId").html(retHtml);
                }
                else {
                    $("#dataPoolTemplateId").html(retHtml);
                }

            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }

    function deleteBookmark(id) {
        var tips = "是否确认删除当前表单?";

        var url = "${pageContext.request.contextPath}/templateSet/deleteBaseReportTemplate";
        Alert(tips, 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: url,
                data: {
                    id: id
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success("操作成功");
                        if ($("#pid").val() == "0") {
                            reloadTableList();
                        }
                        else {
                            reloadSubTableList();
                        }
                    }
                    else {
                        Alert("操作失败，失败原因：" + result.errmsg, 1, null, null);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                }
            });
        });
    }

    function loadTableCloumns() {
        var dataPoolTableId = $("#dataPoolTableId").val();
        if (!dataPoolTableId) {
            return false;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/templateSet/getBaseReportColumnsList",
            type: "get",
            dataType: "json",
            data: {
                tableId: dataPoolTableId
            },
            success: function (result) {
                var retHtml = '<option value="" selected>-请选择-</option>';
                if (result.ret) {

                    $.each(result.data, function (i, item) {
                        retHtml += ' <option value="' + item.id + '">' + item.columnsCnName + '</option>';
                    });
                }
                $("#dataPoolColumnsId").html(retHtml);
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }

    function saveTemplate() {
        if (!$("#frm").valid()) {
            return false;
        }
        Loading.progressShow();
        var data = formParams("frm");
        data["customerId"] = $("#tree_value").val();
        data["entrustId"] = $("#tabs_index_value").val();
        data["reportTypeId"] = $("input[name='reportType']:checked").val();

        $.ajax({
            url: "${pageContext.request.contextPath}/templateSet/saveTemplateData",
            data: data,
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success("保存成功");
                    if ($("#pid").val() == "0") {
                        reloadTableList();
                    }
                    else {
                        reloadSubTableList();
                    }
                    $('#modalTemplate').modal('hide');
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

    function showSubTemplate(id) {
        $("#pid").val(id);
        reloadSubTableList();
        $('#modalSubTemplate').modal({backdrop: 'static', keyboard: false});
    }
</script>




