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
                <h4 class="modal-title">新增档案目录</h4>
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
                                            档案类型<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <select name="fileType" required
                                                    onchange="objArchives.onChangeFileType(this);"
                                                    class="form-control input-full search-select select2">
                                                <option value="">请选择</option>
                                                <c:forEach items="${MarkAdBasePlaceFileDtoList}" var="itemData">
                                                    <option value="${itemData.id}">${itemData.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <label class="col-sm-2 col-form-label">
                                            档案类别<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <select name="fileCategory" required
                                                    class="form-control input-full search-select select2">
                                                <option value="">请先选择类型</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 col-form-label">
                                            档案来源<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <select name="fileSource" required
                                                    class="form-control input-full search-select select2">
                                                <option value="">请选择</option>
                                                <c:forEach items="${FileSourceData}" var="itemData">
                                                    <option value="${itemData.id}">${itemData.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <label class="col-sm-2 col-form-label">
                                            公开方式<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <select name="publicWay" required
                                                    class="form-control input-full search-select select2">
                                                <option value="">请选择</option>
                                                <c:forEach items="${FilePublicData}" var="itemData">
                                                    <option value="${itemData.id}">${itemData.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 col-form-label">
                                            保存期限<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <select name="shelfLife" required
                                                    class="form-control input-full search-select select2">
                                                <option value="">请选择</option>
                                                <c:forEach items="${LifeAdBasePlaceFileDtoList}" var="itemData">
                                                    <option value="${itemData.id}">${itemData.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <label class="col-sm-2 control-label">
                                            排序
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="排序" name="sorting" data-rule-number='true'
                                                   class="form-control input-full">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <div class="col-sm-2">
                                        </div>
                                        <div class="col-sm-4">
                                            <div class="form-check" style="justify-content:left">
                                                <label class="form-check-label">
                                                    <input class="form-check-input" type="checkbox"
                                                           name="bisBinding" value="true">
                                                    <span class="form-check-sign">装订存档</span>
                                                </label>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="objArchives.saveArchives()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<div id="AdPlaceFileItemDetailDtoListBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">档案记录</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <p id="toolbarAdPlaceFileItemDetail">

                            <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                    data-toggle="modal" onclick="objArchives.addAdPlaceFileItemDetailBox()">
                            <span class="btn-label">
                            <i class="fa fa-plus"></i>
                            </span>
                                新增
                            </button>

                            <button style="margin-left: 5px" class="btn btn-warning btn-sm" type="button"
                                    onclick="objArchives.batchDeleteAdPlaceFileItemDetail()">
                            <span class="btn-label">
                            <i class="fa fa-minus"></i>
                            </span>
                                批量删除
                            </button>

                        </p>
                        <table id="AdPlaceFileItemDetailDtoTable" class="table table-bordered"></table>
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

<div id="AdPlaceFileItemDetailDtoBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
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
                    <input type="hidden" name="masterId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 col-form-label">
                                            档案名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" required placeholder="档案名称" name="name"
                                                   class="form-control input-full">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            排序
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="排序" name="sorting" data-rule-number='true'
                                                   class="form-control input-full">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 col-form-label">
                                            备注
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea placeholder="备注" name="remark"
                                                      class="form-control input-full"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-2 control-label">
                                            档案附件
                                        </label>
                                        <div class="col-sm-6">
                                            <input id="project_proxy"
                                                   name="project_proxy"
                                                   required="required" placeholder="档案附件"
                                                   class="form-control input-full"
                                                   type="file">
                                            <div id="_project_proxy"></div>
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
                        onclick="objArchives.saveAdPlaceFileItemDetailDto()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<div id="div_Archives_group_box" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">设置卷号</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body" style="min-height: 160px;">
                <form class="form-horizontal">
                    <input type="hidden" name="id">
                    <div class="row">
                        <div class="col-md-12">
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
                                                        生成卷号
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
                                                        选择卷号
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
                        <p id="toolbarSub">
                        </p>
                        <table id="tbAdPlaceFileVolumeNumberList" class="table table-bordered"></table>
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
                                    <input type="hidden" name="publicProjectId" value="${projectInfo.id}">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline">
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
                                                <label class="col-sm-1 control-label">
                                                    档案来源
                                                </label>
                                                <div class="col-sm-2">
                                                    <select name="fileSource" required
                                                            class="form-control input-full search-select select2">
                                                        <option value="">请选择</option>
                                                        <c:forEach items="${FileSourceData}" var="itemData">
                                                            <option value="${itemData.id}">${itemData.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    公开方式
                                                </label>
                                                <div class="col-sm-2">
                                                    <select name="publicWay" required
                                                            class="form-control input-full search-select select2">
                                                        <option value="">请选择</option>
                                                        <c:forEach items="${FilePublicData}" var="itemData">
                                                            <option value="${itemData.id}">${itemData.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline">
                                                <label class="col-sm-1 control-label">
                                                    装订存档
                                                </label>
                                                <div class="col-sm-2">
                                                    <select name="bisBinding"
                                                            class="form-control input-full search-select select2">
                                                        <option value="">请选择</option>
                                                        <option value="true">是</option>
                                                        <option value="false">否</option>
                                                    </select>
                                                </div>
                                                <div class="col-sm-6">
                                                    <button type="button" class="btn btn-info btn-sm"
                                                            onclick="objArchives.loadTableList(this);">
                                                        <span class="btn-label"><i class="fa fa-search"></i></span>
                                                        查询
                                                    </button>
                                                    <button class="btn btn-success btn-sm" style="margin-left: 5px;"
                                                            type="button"
                                                            onclick="objArchives.appendArchives(0) ;">
                                                        <span class="btn-label"><i
                                                                class="fa fa-plus"></i></span>添加
                                                    </button>
                                                    <button type="button" class="btn btn-primary btn-sm"
                                                            style="margin-left: 5px;"
                                                            onclick="objArchives.autoCreateProjectFileCompleteNow(this);">
                                                        <span class="btn-label"><i
                                                                class="fab fa-autoprefixer"></i></span>
                                                        自动创建档案
                                                    </button>
                                                    <button type="button" class="btn btn-primary btn-sm"
                                                            style="margin-left: 5px;"
                                                            onclick="objArchives.batchGroupHandel() ;">
                                                            <span class="btn-label"><i
                                                                    class="fa fa-cog"></i></span>
                                                        设置卷号
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <table class="table table-bordered" id="tb_Archives_list"></table>
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
    objArchives.tableName = "tb_ad_place_file_item_detail_dto";
    objArchives.fieldsName = "project_proxy";

    objArchives.adPlaceFileItemDetailBox = $("#AdPlaceFileItemDetailDtoBox");
    objArchives.adPlaceFileItemDetailListBox = $("#AdPlaceFileItemDetailDtoListBox");
    objArchives.adPlaceFileItemDetailTable = $("#AdPlaceFileItemDetailDtoTable");

    objArchives.masterId = 0;

    objArchives.volumeNumberAddBox = $("#div_AdPlaceFileVolumeNumber_box");
    objArchives.volumeNumberBox = $("#div_AdPlaceFileVolumeNumberDto_box");
    objArchives.volumeNumberTable = $("#tbAdPlaceFileVolumeNumberList");
    objArchives.groupBox = $("#div_Archives_group_box");


    ///-------------------------------------------------|档案卷和操作|----------------------------------------
    //
    objArchives.batchGroupHandel = function () {
        var table = $(objArchives.table.selector);
        var rows = table.bootstrapTable('getSelections');
        if (rows.length == 0) {
            notifyWarning("提示", "请选择要设置的档案!");
            return false;
        }
        var count = 0;
        var idArray = [];
        var idFilterArray = [];
        $.each(rows, function (k, item) {
            if (item.groupId) {
                count++;
            }
            idArray.push(item.id);
            if (item.bisBinding) {
                idFilterArray.push(item.id);
            }
        });
        if (count != 0) {
            notifyWarning("提示", "存在已经设置了卷号的档案");
            return false;
        }
        if (idFilterArray.length != rows.length) {
            notifyWarning("提示", "只有永久存档的档案才能选择卷号");
            return false;
        }
        var box = $(objArchives.groupBox.selector);
        var frm = box.find("form");
        frm.clearAll();
        frm.validate();
        objArchives.targetId = idArray.join(",");
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
        AssessCommon.ajaxServerFun({
            formData: JSON.stringify(data),
            targetId: targetId
        }, "/projectArchives/saveAdPlaceFileGroupDto", "post", function () {
            AlertSuccess("成功", "数据已成功保存到数据库");
            objArchives.loadTableList();
            box.modal("hide");
        });
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
                // str += '<button type="button" onclick="objArchives.editAdPlaceFileVolumeNumberDto(' + row.id + ')"  style="margin-left: 5px;"  class="btn   btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                // str += '<i class="fa fa-pen"></i>';
                // str += '</button>';

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
        TableInit(table, "${pageContext.request.contextPath}/projectArchives/getAdPlaceFileVolumeNumberDtoListByParam", cols, {}, {
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


    objArchives.selectAdPlaceFileVolumeNumberDto = function (id) {
        var table = $(objArchives.volumeNumberTable.selector);
        var data = table.bootstrapTable('getRowByUniqueId', id);
        var box = $(objArchives.groupBox.selector);
        var frm = box.find("form");
        frm.find("[name=number]").val(data.number);
        $(objArchives.volumeNumberBox.selector).modal("hide");
    };

    objArchives.delAdPlaceFileVolumeNumberDto = function (id) {
        AssessCommon.ajaxServerFun({id: id}, "/projectArchives/deleteAdPlaceFileVolumeNumberDtoByIds", "post", function () {
            objArchives.loadTableAdPlaceFileVolumeNumberDtoList();
        }, "delete");
    };

    objArchives.addAdPlaceFileVolumeNumberDto = function () {
        var box = $(objArchives.volumeNumberAddBox.selector);
        box.modal("show");
        var frm = box.find("form");
        frm.clearAll();
        frm.validate();
    };

    objArchives.saveBaseAdPlaceFileVolumeNumberDto = function (data, callback) {
        AssessCommon.ajaxServerFun({formData: JSON.stringify(data)}, "/projectArchives/saveAdPlaceFileVolumeNumberDto", "post", callback);
    };


    objArchives.getAdPlaceFileGroupDtoById = function (id, callback) {
        AssessCommon.ajaxServerFun({id: id}, "/projectArchives/getAdPlaceFileGroupDtoById", "get", callback);
    };

    objArchives.autoFileNumber = function (_this) {
        var frm = $(_this).closest("form");
        var data = formSerializeArray(frm);
        if (!data.ruleNumber) {
            notifyInfo("提示", "必须选择卷号规则才能生成卷号");
            return false;
        }
        AssessCommon.ajaxServerFun({ruleId: data.ruleNumber}, "/projectArchives/symbol", "get", function (item) {
            frm.find("[name=number]").val(item.symbol);
            AlertSuccess("成功", "已经成功获取自动生成的卷号");
        });
    };

    objArchives.changeFileNumber = function (_this, ruleNumber) {
        var frm = $(_this).closest("form");
        AssessCommon.ajaxServerFun({ruleId: ruleNumber}, "/projectArchives/symbol", "get", function (item) {
            var symbol = item.symbol;
            frm.find("[name=number]").val(symbol);
            objArchives.saveBaseAdPlaceFileVolumeNumberDto({number: symbol, ruleNumber: ruleNumber}, function () {
                AlertSuccess("成功", "已经成功获取自动生成的卷号");
            });
        });
    };
    ///-------------------------------------------------||----------------------------------------

    //档案 目录 类型和类别的下拉框切换
    objArchives.onChangeFileType = function (_this, fileCategory) {
        var frm = $(_this).closest("form");
        var value = $(_this).find("option:selected").val();
        if (!value) {
            return false;
        }
        AssessCommon.ajaxServerFun({pid: value}, "/projectArchives/getAdBasePlaceFileListByPid", "get", function (data) {
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
        });
    };

    //档案 目录 list
    objArchives.loadTableList = function (_this) {
        var data = {publicProjectId: '${projectInfo.id}'};
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
        cols.push({
            field: 'fileCategoryName', title: '档案类别', width: "20%", formatter: function (value, row) {
                return value+"<span style='color: red;'>【"+row.count+"】</spna>";
            }
        });
        cols.push({field: 'fileTypeName', title: '档案类型', width: "10%"});
        cols.push({field: 'fileSourceName', title: '档案来源', width: "10%"});
        cols.push({field: 'publicWayName', title: '公开方式', width: "10%"});
        cols.push({field: 'shelfLifeName', title: '保存期限', width: "10%"});
        cols.push({field: 'groupNumber', title: '卷号', width: "15%"});
        cols.push({
            field: 'bisBinding', title: '装订存档', width: "7%", formatter: function (value, row) {
                if (value) {
                    return "是";
                } else {
                    return "否";
                }
            }
        });


        cols.push({
            field: 'id', title: '操作', width: "15%", formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';

                str += '<button type="button" onclick="objArchives.openAdPlaceFileItemDetailBox(' + row.id + ')"  style="margin-left: 5px;"  class="btn btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="档案列表">';
                str += '<i class="fas fa-list-ul"></i>';
                str += '</button>';

                if (row.groupId) {
                    str += '<button type="button" onclick="objArchives.lockOpenDataDic(' + row.id + ')"  style="margin-left: 5px;"  class="btn btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="解除卷号绑定">';
                    str += '<i class="fas fa-lock-open"></i>';
                    str += '</button>';
                } else {
                    str += '<button type="button" onclick="objArchives.editDataDic(' + row.id + ')"  style="margin-left: 5px;"  class="btn   btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                    str += '<i class="fa fa-pen"></i>';
                    str += '</button>';

                    str += '<button type="button" onclick="objArchives.delDataDic(' + row.id + ')"  style="margin-left: 5px;"  class="btn   btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                    str += '<i class="fa fa-minus"></i>';
                    str += '</button>';
                }
                return str;
            }
        });
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
        }, true);
    };

    //档案 目录 add box
    objArchives.appendArchives = function (id) {
        var box = $(objArchives.box.selector);
        box.modal("show");
        var frm = box.find("form");
        frm.clearAll();
        frm.validate();
    };

    //档案 目录 delete
    objArchives.delDataDic = function (id) {
        AssessCommon.ajaxServerMethod({masterId: id}, "/projectArchives/getAdPlaceFileItemDetailDtoCount", "get", function (count) {
            count = Number(count);
            if (count > 0) {
                notifyInfo("提示", "档案目录下有档案不能删除,需要清除档案记录才能删除档案目录");
                return false;
            }
            AssessCommon.ajaxServerFun({id: id}, "/projectArchives/deleteAdPlaceFileItemDtoByIds", "post", function () {
                objArchives.loadTableList();
            }, "delete");
        });
    };

    //档案 目录 lock data
    objArchives.lockOpenDataDic = function (id) {
        AlertConfirm("是否确认解除卷号设置", "解除相应的数据后将不可恢复可以重新再次设置卷号", function () {
            AssessCommon.ajaxServerFun({id: id}, "/projectArchives/lockOpenDataDicAdPlaceFileItemDtoById", "post", function () {
                notifySuccess("成功", "解除卷号成功");
                objArchives.loadTableList();
            });
        });
    };

    //档案 目录 auto save data
    objArchives.autoCreateProjectFileCompleteNow = function () {
        AlertConfirm("提示", "是否自动创建档案", function () {
            AssessCommon.ajaxServerFun({projectId: '${projectInfo.id}'}, "/projectArchives/autoCreateProjectFileCompleteNow", "post", function () {
                notifySuccess("成功", "创建档案成功");
                objArchives.loadTableList();
            });
        })
    };

    //档案 目录 edit data
    objArchives.editDataDic = function (id) {
        objArchives.appendArchives(id);
        var frm = $(objArchives.box.selector).find("form");
        var table = $(objArchives.table.selector);
        var data = table.bootstrapTable('getRowByUniqueId', id);
        frm.initForm(data);
        var ele = frm.find("select[name='fileType']")[0];
        frm.find("select[name='fileType']").val(data.fileType).trigger('change');
        frm.find("select[name='fileSource']").val(data.fileSource).trigger('change');
        frm.find("select[name='publicWay']").val(data.publicWay).trigger('change');
        frm.find("select[name='shelfLife']").val(data.shelfLife).trigger('change');

        setTimeout(function () {
            frm.find("select[name='fileCategory']").val(data.fileCategory).trigger('change');
        }, 450);
    };

    //档案 目录 server data
    objArchives.saveArchives = function () {
        var box = $(objArchives.box.selector);
        var frm = box.find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        data.publicProjectId = '${projectInfo.id}';
        data.publicProjectName = '${projectInfo.projectName}';
        if (!data.bisBinding) {
            data.bisBinding = false;
        }
        AssessCommon.ajaxServerFun({formData: JSON.stringify(data)}, "/projectArchives/saveAdPlaceFileItemDto", "post", function () {
            AlertSuccess("成功", "数据已成功保存到数据库");
            objArchives.loadTableList();
            box.modal("hide");
        });
    };

    //档案记录 table box
    objArchives.openAdPlaceFileItemDetailBox = function (masterId) {
        objArchives.masterId = masterId;
        var box = $(objArchives.adPlaceFileItemDetailListBox.selector);
        box.modal("show");

        objArchives.loadTableAdPlaceFileItemDetailList();
    };


    //档案记录 table list
    objArchives.loadTableAdPlaceFileItemDetailList = function () {
        var table = $(objArchives.adPlaceFileItemDetailTable.selector);
        var cols = [];
        cols.push({field: 'name', title: '档案名称', width: "40%"});
        cols.push({field: 'fileViewName', title: '文档', width: "40%"});
        cols.push({
            field: 'id', title: '操作', width: "15%", formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';

                str += '<button type="button" onclick="objArchives.editAdPlaceFileItemDetailDto(' + row.id + ')"  style="margin-left: 5px;"  class="btn   btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';

                str += '<button type="button" onclick="objArchives.delAdPlaceFileItemDetailDto(' + row.id + ')"  style="margin-left: 5px;"  class="btn   btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
                return str;
            }
        });
        table.bootstrapTable('destroy');
        TableInit(table, "${pageContext.request.contextPath}/projectArchives/getAdPlaceFileItemDetailDtoListByParam", cols, {masterId: objArchives.masterId}, {
            method: "get",
            showColumns: false,
            showRefresh: false,
            search: false,
            striped: true,
            toolbar: '#toolbarAdPlaceFileItemDetail',
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        }, true);
    };

    //档案记录 server data
    objArchives.saveAdPlaceFileItemDetailDto = function () {
        var box = $(objArchives.adPlaceFileItemDetailBox.selector);
        box.modal("show");
        var frm = box.find("form");
        var data = formSerializeArray(frm);
        data.masterId = objArchives.masterId;
        AssessCommon.ajaxServerFun({formData: JSON.stringify(data)}, "/projectArchives/saveAdPlaceFileItemDetailDto", "post", function () {
            AlertSuccess("成功", "数据已成功保存到数据库");
            objArchives.loadTableAdPlaceFileItemDetailList();
            box.modal("hide");
        });
    };

    //档案记录 delete
    objArchives.delAdPlaceFileItemDetailDto = function (id) {
        AssessCommon.ajaxServerFun({id: id}, "/projectArchives/deleteAdPlaceFileItemDetailDtoByIds", "post", function () {
            objArchives.loadTableAdPlaceFileItemDetailList();
            objArchives.loadTableList();
        }, "delete");
    };

    //档案记录 edit
    objArchives.editAdPlaceFileItemDetailDto = function (id) {
        var table = $(objArchives.adPlaceFileItemDetailTable.selector);
        var data = table.bootstrapTable('getRowByUniqueId', id);
        objArchives.addAdPlaceFileItemDetailBox(data);

    };

    //档案记录 add box
    objArchives.addAdPlaceFileItemDetailBox = function (option) {
        var data = {id: 0};
        if (option) {
            data = $.extend(data, option);
        }
        var box = $(objArchives.adPlaceFileItemDetailBox.selector);
        box.modal("show");
        var frm = box.find("form");
        frm.clearAll();
        frm.validate();
        frm.initForm(data);
        var tableName = objArchives.tableName;
        var fieldsName = objArchives.fieldsName;
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                tableName: tableName,
                tableId: data.id,
                fieldsName: fieldsName,
                // projectId: id
            },
            deleteFlag: true
        });
        FileUtils.uploadFiles({
            target: fieldsName,
            disabledTarget: "btn_submit",
            formData: {
                tableName: tableName,
                tableId: data.id,
                fieldsName: fieldsName,
                // projectId: id
            },
            deleteFlag: true
        });
    };

    //档案记录 批量删除
    objArchives.batchDeleteAdPlaceFileItemDetail = function () {
        var table = $(objArchives.adPlaceFileItemDetailTable.selector);
        var rows = table.bootstrapTable('getSelections');
        if (rows.length == 0) {
            notifyWarning("提示", "请选择要删除的档案记录!");
            return false;
        }
        var idArray = [];
        $.each(rows, function (k, item) {
            idArray.push(item.id);
        });
        objArchives.delAdPlaceFileItemDetailDto(idArray.join(","));
    };


    function showOtherQuery(_that) {
        $("#showOtherQuery").toggle();
        var c = $(_that).find("i").attr("class");
        if (c.indexOf("plus") != -1) {
            $(_that).find("i").attr("class", "fa fa-minus-circle");
        } else {
            $(_that).find("i").attr("class", "fa fa-plus-circle");
        }

    }

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

