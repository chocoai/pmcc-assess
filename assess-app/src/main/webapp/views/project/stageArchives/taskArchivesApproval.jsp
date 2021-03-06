<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>

<div id="div_Archives_group_box" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">档案分组</h4>
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
                                            档案存放位置<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" required placeholder="档案存放位置" name="saveLocation"
                                                   class="form-control input-full">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 col-form-label">
                                            档案卷号<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <div class="input-group">
                                                <input type="text" placeholder="档案卷号" required
                                                       name="number"
                                                       class="form-control">

                                                <div class="input-group-append">
                                                    <button class="btn btn-success btn-sm dropdown-toggle "
                                                            type="button"
                                                            data-toggle="dropdown" aria-haspopup="true"
                                                            aria-expanded="false">
                                                    <span class="btn-label">
												        <i class="fa fa-plus"></i>
											        </span>
                                                        自动生成卷号
                                                    </button>
                                                    <div class="dropdown-menu"
                                                         style="position: absolute; transform: translate3d(410px, 43px, 0px); top: 0px; left: 0px; will-change: transform;">
                                                        <c:forEach items="${SysSymbolRuleDtoList}" var="itemData">
                                                            <a class="dropdown-item"
                                                               onclick="objArchives.changeFileNumber(this,'${itemData.id}');"
                                                               href="javascript:void(0)">${itemData.numberRule}</a>
                                                        </c:forEach>
                                                    </div>
                                                </div>


                                                <div class="input-group-prepend">
                                                    <button class="btn btn-primary btn-sm "
                                                            style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                            type="button"
                                                            onclick="objArchives.openNumberModal(this);">
                                                        编辑
                                                    </button>


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
                <button type="button" class="btn btn-primary btn-sm" onclick="objArchives.saveGroupArchives()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<div id="div_AdPlaceFileVolumeNumberDto_box" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">卷号记录</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <table id="tbAdPlaceFileVolumeNumberList" class="table table-bordered"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div id="div_AdPlaceFileVolumeNumber_box" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">新增卷号</h4>
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
                                            卷号<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="档案卷号" required
                                                   name="number"
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
                <button type="button" class="btn btn-primary btn-sm"
                        onclick="objArchives.saveAdPlaceFileVolumeNumberDto()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="page-inner mt--5">
                <div class="row mt--2">
                    <%@include file="/views/share/project/projectInfoSimple.jsp" %>
                    <%@include file="/views/share/project/projectPlanDetails.jsp" %>
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
                                    <input type="hidden" name="publicProjectId" value="${projectInfo.id}">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline">
                                                <label class="col-sm-1 control-label">
                                                    档案名称
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-maxlength="50" placeholder="档案名称"
                                                           name="name"
                                                           class="form-control input-full">
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    档案类型
                                                </label>
                                                <div class="col-sm-2">
                                                    <select name="fileType"
                                                            onchange="objArchives.onChangeFileType(this);"
                                                            class="form-control input-full search-select select2">
                                                        <option value="">请选择</option>
                                                        <c:forEach items="${MarkAdBasePlaceFileDtoList}" var="itemData">
                                                            <option value="${itemData.id}">${itemData.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    档案类别
                                                </label>
                                                <div class="col-sm-2">
                                                    <select name="fileCategory"
                                                            class="form-control input-full search-select select2">
                                                        <option value="">请先选择类型</option>
                                                    </select>
                                                </div>

                                                <div class="col-sm-3">
                                                    <button type="button" class="btn btn-info btn-sm"
                                                            onclick="objArchives.loadTableList(this);">
                                                        <span class="btn-label"><i class="fa fa-search"></i></span>
                                                        查询
                                                    </button>
                                                    <c:if test="${xing_zheng_node == activityReName}">
                                                        <button type="button" class="btn btn-primary btn-sm"
                                                                onclick="objArchives.batchGroupHandel() ;">
                                                            <span class="btn-label"><i
                                                                    class="fa fa-cog"></i></span>
                                                            批量设置卷号
                                                        </button>
                                                    </c:if>
                                                    <button style="margin-left: 10px"
                                                            class="btn btn-sm btn-info  btn-border"
                                                            type="button"
                                                            onclick="showOtherQuery(this)">
                                                    <span class="btn-label">
												<i class="fa fa-plus-circle"></i>
											</span>
                                                        高级查询
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div id="showOtherQuery" style="display: none">
                                        <div class="row form-group">
                                            <div class="col-md-12">
                                                <div class="form-inline">
                                                    <%--<label class="col-sm-1 control-label">--%>
                                                        <%--项目名称--%>
                                                    <%--</label>--%>
                                                    <%--<div class="col-sm-2">--%>
                                                        <%--<input type="text" data-rule-maxlength="50" placeholder="项目名称"--%>
                                                               <%--name="publicProjectName"--%>
                                                               <%--class="form-control input-full">--%>
                                                    <%--</div>--%>
                                                    <label class="col-sm-1 control-label">
                                                        是否装订存档
                                                    </label>
                                                    <div class="col-sm-2">
                                                        <select name="bisBinding"
                                                                class="form-control input-full search-select select2">
                                                            <option value="">请选择</option>
                                                            <option value="true">是</option>
                                                            <option value="false">否</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                    </div>

                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <table class="table table-bordered" id="tb_Archives_list"></table>
                                        </div>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>


                    <%@include file="/views/share/form_approval.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>


<script>

    var objArchives = {};


    objArchives.targetId = 0;

    objArchives.table = $("#tb_Archives_list");
    objArchives.box = $("#div_Archives_box");
    objArchives.volumeNumberAddBox = $("#div_AdPlaceFileVolumeNumber_box");
    objArchives.volumeNumberBox = $("#div_AdPlaceFileVolumeNumberDto_box");
    objArchives.volumeNumberTable = $("#tbAdPlaceFileVolumeNumberList");
    objArchives.groupBox = $("#div_Archives_group_box");

    objArchives.onChangeFileType = function (_this) {
        var frm = $(_this).closest("form");
        var value = $(_this).find("option:selected").val();
        if (!value) {
            return false;
        }
        AssessCommon.ajaxServerFun({pid:value},"/projectArchives/getAdBasePlaceFileListByPid" ,"get",function (data) {
            var retHtml = '';
            retHtml += '<option value="" selected>-请选择-</option>';
            $.each(data, function (k, item) {
                retHtml += '<option key="' + item.name + '" title="' + item.name + '" value="' + item.id + '"';
                if (fileCategory) {
                    if (item.id == fileCategory) {
                        retHtml += 'selected="selected"'
                    }
                }
                retHtml += '>' + item.name + '</option>';
            });
            frm.find("select[name='fileCategory']").empty().html(retHtml).trigger('change');
            if (fileCategory) {
                frm.find("select[name='fileCategory']").val(fileCategory).trigger('change');
            }
        } ) ;
    };

    objArchives.loadTableList = function (_this) {
        var data = {publicProjectId: '${projectPlanDetails.projectId}'};
        if (_this) {
            data = formSerializeArray($(_this).closest("form"));
        }
        var arr = Object.keys(data);
        $.each(arr, function (i, item) {
            if (!data[item]) {
                data[item] = undefined;
            }
        });
        console.log(data);
        var table = $(objArchives.table.selector);
        var cols = [];
        cols.push({field: 'name', title: '档案名称', width: "10%"});

        cols.push({field: 'fileTypeName', title: '档案类型', width: "5%"});
        cols.push({field: 'fileCategoryName', title: '档案类别', width: "5%"});
        cols.push({field: 'fileSourceName', title: '档案来源', width: "5%"});
        cols.push({field: 'publicWayName', title: '公开方式', width: "5%"});
        cols.push({field: 'shelfLifeName', title: '保存期限', width: "5%"});


        cols.push({
            field: 'groupId', title: '卷号设置与否', width: "7%", formatter: function (value, row) {
                if (row.groupId) {
                    return "是";
                } else {
                    return "否";
                }
            }
        });
        cols.push({
            field: 'bisBinding', title: '必须设置卷号', width: "7%", formatter: function (value, row) {
                if (value) {
                    return "是";
                } else {
                    return "否";
                }
            }
        });
        cols.push({field: 'count', title: '档案记录数', width: "5%"});
        cols.push({field: 'saveLocation', title: '存储位置', width: "15%"});
        cols.push({field: 'number', title: '存放卷号', width: "5%"});
        var checkbox = '${xing_zheng_node}' == '${activityReName}';
        if (checkbox) {
            cols.push({
                field: 'id', title: '操作', width: "15%", formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';

                    str += '<button type="button" onclick="objArchives.groupHandel(' + row.id + ')" style="margin-left: 5px;" class="btn   btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="档案分组">';
                    str += '<i class="fa fa-cog"></i>';
                    str += '</button>';
                    return str;
                }
            });
        }

        table.bootstrapTable('destroy');
        TableInit(table, "${pageContext.request.contextPath}/projectArchives/getAdPlaceFileItemDtoListByParam", cols, data, {
            method: "get",
            showColumns: false,
            showRefresh: false,
            search: false,
            striped: true,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        }, checkbox);
    };

    objArchives.batchGroupHandel = function () {
        var table = $(objArchives.table.selector);
        var rows = table.bootstrapTable('getSelections');
        if (rows.length == 0) {
            notifyWarning("提示", "请选择要设置的档案!");
            return false;
        }
        var count = 0;
        var idArray = [];
        $.each(rows, function (k, item) {
            if (item.groupId) {
                count++;
            }
            idArray.push(item.id);
        });
        if (count != 0) {
            notifyWarning("提示", "存在已经设置了卷号的档案");
            return false;
        }
        var box = $(objArchives.groupBox.selector);
        var frm = box.find("form");
        frm.clearAll();
        frm.validate();
        objArchives.targetId = idArray.join(",");
        box.modal("show");
    };

    objArchives.groupHandel = function (id) {
        var box = $(objArchives.groupBox.selector);
        var frm = box.find("form");
        frm.clearAll();
        frm.validate();
        objArchives.targetId = id;
        var table = $(objArchives.table.selector);
        var data = table.bootstrapTable('getRowByUniqueId', id);
        if (data.groupId) {
            objArchives.getAdPlaceFileGroupDtoById(data.groupId, function (item) {
                frm.initForm(item);
            });
        }
        box.modal("show");
    };

    objArchives.saveGroupArchives = function () {
        var box = $(objArchives.groupBox.selector);
        var frm = box.find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        var targetId = null;
        //更新的时候不需要再次关联档案
        if (!data.id) {
            targetId = objArchives.targetId;
        }
        AssessCommon.ajaxServerFun({formData: JSON.stringify(data), targetId: targetId},"/projectArchives/saveAdPlaceFileGroupDto" ,"post",function () {
            AlertSuccess("成功", "数据已成功保存到数据库");
            objArchives.loadTableList();
            box.modal("hide");
        } ) ;
    };

    objArchives.openNumberModal = function () {
        var box = $(objArchives.volumeNumberBox.selector);
        box.modal("show");
        objArchives.loadTableAdPlaceFileVolumeNumberDtoList();
    };

    objArchives.loadTableAdPlaceFileVolumeNumberDtoList = function () {
        var table = $(objArchives.volumeNumberTable.selector);
        var cols = [];
        cols.push({field: 'number', title: '卷号', width: "30%"});
        cols.push({field: 'saveLocation', title: '存放位置', width: "30%"});
        cols.push({
            field: 'id', title: '操作', width: "25%", formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                // str += '<button type="button" onclick="objArchives.delAdPlaceFileVolumeNumberDto(' + row.id + ')"  style="margin-left: 5px;"  class="btn   btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                // str += '<i class="fa fa-minus"></i>';
                // str += '</button>';
                str += '<button type="button" onclick="objArchives.selectAdPlaceFileVolumeNumberDto(' + row.id + ')"  style="margin-left: 5px;"  class="btn   btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="选择此卷号">';
                str += '<i class="fa fa-check"></i>';
                str += '</button>';
                return str;
            }
        });
        table.bootstrapTable('destroy');
        TableInit(table, "${pageContext.request.contextPath}/projectArchives/getAdPlaceFileVolumeNumberDtoListByParam", cols, {activityReName:'${activityReName}'}, {
            method: "get",
            showColumns: false,
            showRefresh: false,
            search: false,
            striped: true,
            toolbar: '#toolbarSub',
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        });
    };

    objArchives.editAdPlaceFileVolumeNumberDto = function (id) {
        objArchives.addAdPlaceFileVolumeNumberDto();
        var frm = $(objArchives.volumeNumberAddBox.selector).find("form");
        var table = $(objArchives.volumeNumberTable.selector);
        var data = table.bootstrapTable('getRowByUniqueId', id);
        frm.initForm(data);
    };

    objArchives.selectAdPlaceFileVolumeNumberDto = function (id) {
        var table = $(objArchives.volumeNumberTable.selector);
        var data = table.bootstrapTable('getRowByUniqueId', id);
        var box = $(objArchives.groupBox.selector);
        var frm = box.find("form");
        frm.find("[name=number]").val(data.number);
        $(objArchives.volumeNumberBox.selector).modal("hide");
    };

    objArchives.delAdPlaceFileVolumeNumberDto = function (id) {
        AssessCommon.ajaxServerFun({id:id},"/projectArchives/deleteAdPlaceFileVolumeNumberDtoByIds" ,"post",function () {
            objArchives.loadTableAdPlaceFileVolumeNumberDtoList();
        } ,"delete") ;
    };

    objArchives.addAdPlaceFileVolumeNumberDto = function () {
        var box = $(objArchives.volumeNumberAddBox.selector);
        box.modal("show");
        var frm = box.find("form");
        frm.clearAll();
        frm.validate();
    };

    objArchives.saveBaseAdPlaceFileVolumeNumberDto = function (data, callback) {
        AssessCommon.ajaxServerFun({formData: JSON.stringify(data)},"/projectArchives/saveAdPlaceFileVolumeNumberDto" ,"post",callback) ;
    };

    objArchives.saveAdPlaceFileVolumeNumberDto = function () {
        var box = $(objArchives.volumeNumberAddBox.selector);
        var frm = box.find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        objArchives.saveBaseAdPlaceFileVolumeNumberDto(data, function () {
            AlertSuccess("成功", "数据已成功保存到数据库");
            objArchives.loadTableAdPlaceFileVolumeNumberDtoList();
            box.modal("hide");
        });
    };

    objArchives.getAdPlaceFileGroupDtoById = function (id, callback) {
        AssessCommon.ajaxServerFun({id:id},"/projectArchives/getAdPlaceFileGroupDtoById" ,"get",callback ) ;
    };

    objArchives.autoFileNumber = function (_this) {
        var frm = $(_this).closest("form");
        var data = formSerializeArray(frm);
        if (!data.ruleNumber) {
            notifyInfo("提示", "必须选择卷号规则才能生成卷号");
            return false;
        }
        AssessCommon.ajaxServerFun({ruleId: data.ruleNumber},"/projectArchives/symbol" ,"get",function (item) {
            frm.find("[name=number]").val(item.symbol);
            AlertSuccess("成功", "已经成功获取自动生成的卷号");
        } ) ;
    };

    objArchives.changeFileNumber = function (_this, ruleNumber) {
        var frm = $(_this).closest("form");
        AssessCommon.ajaxServerFun({ruleId: ruleNumber},"/projectArchives/symbol" ,"get",function (item) {
            var symbol = item.symbol;
            frm.find("[name=number]").val(symbol);
            objArchives.saveBaseAdPlaceFileVolumeNumberDto({number: symbol,ruleNumber:ruleNumber}, function () {
                AlertSuccess("成功", "已经成功获取自动生成的卷号");
            });
        } ) ;
    };

    $(document).ready(function () {
        objArchives.loadTableList();
    });

</script>


<script type="text/javascript">

    function getAdPlaceFileItemDtoValidList(callback) {
        AssessCommon.ajaxServerFun( {projectId: '${projectInfo.id}'},"/projectArchives/getAdPlaceFileItemDtoValidList" ,"get",callback) ;
    }

    function saveform() {

        if ('${xing_zheng_node}' == '${activityReName}') {
            getAdPlaceFileItemDtoValidList(function (arr) {
                console.log(arr);
                if (arr.length != 0) {
                    notifyInfo("提示", "存在没有设置卷号的档案");
                    return false;
                }
                saveApprovalform("");
            });
        } else {
            saveApprovalform("");
        }


    }
</script>
</html>
