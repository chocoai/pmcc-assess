<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<div id="boxSurveyAssetInfoGroup" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 50%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">添加资产清查组</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="inventoryId">
                    <input type="hidden" name="assetInfoId">
                    <input type="hidden" name="declareIds">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                                组名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                                <input type="text" class="form-control input-full" name="groupName"
                                                       placeholder="组名称" required="required">
                                            </div>
                                        </div>
                                        <div class="form-inline x-valid" style="margin-top: 30px;">
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                                组权证列表
                                            </label>
                                            <span class="boxSurveyAssetInfoGroup">
                                <button type="button" class="btn btn-warning  btn-sm"
                                        onclick="assetInfo.removeGroupSurveyAssetInfoItem() ;">
                                    <i class="fa fa-minus"></i>
                                    移除
                                </button>
                                </span>
                                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                                <table class="table table-bordered" id="tb_infoItem_list"></table>
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
                        onclick="assetInfo.saveSurveyAssetInfoGroup();">
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
                    <div class="col-md-12">
                        <div class="x_panel card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="x_title card-title">
                                        清查权证
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
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
                                                    权证号
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-maxlength="50" placeholder="权证号"
                                                           name="name"
                                                           class="form-control input-full">
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    坐落
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-maxlength="50" placeholder="坐落"
                                                           name="seat"
                                                           class="form-control input-full">
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    状态
                                                </label>
                                                <div class="col-sm-2">
                                                    <select name="inventoryStatus" class="form-control input-full">
                                                        <option value="">请选择</option>
                                                        <option value="finish">已清查</option>
                                                        <option value="runing">待清查</option>
                                                    </select>
                                                </div>
                                                <div class="col-sm-3">
                                                    <button type="button" class="btn btn-info btn-sm"
                                                            onclick="assetInfo.loadDeclareRecordList(this);">
                                                        <span class="btn-label"><i class="fa fa-search"></i></span>
                                                        查询
                                                    </button>
                                                    <button class="btn btn-success btn-sm" style="margin-left: 5px;"
                                                            type="button"
                                                            onclick="assetInfo.selectClaimData(this) ;">
                                                        <span class="btn-label"><i
                                                                class="fa fa-plus"></i></span>添加到认领的权证列表
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <table class="table table-bordered" id="tb_declare_record_list"></table>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="x_panel card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="x_title card-title">
                                        认领的权证
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form class="form-horizontal" id="queryForm">
                                    <input type="hidden" name="inventoryId">
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline">
                                                <label class="col-sm-1 control-label">
                                                    楼盘
                                                </label>
                                                <div class="col-sm-2">
                                                    <select name="estate" data-child-type="building"
                                                            data-for-name="building"
                                                            onchange="assetInfo.getBasicApplyBatchDetailListByType(this);"
                                                            class="form-control input-full">
                                                        <option value="">请选择</option>
                                                        <c:forEach var="item" items="${exploreEstateList}">
                                                            <option value="${item.id}"
                                                                    data-apply-batch-id="${item.applyBatchId}">${item.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    楼栋
                                                </label>
                                                <div class="col-sm-2">
                                                    <select name="building" data-child-type="unit" data-for-name="unit"
                                                            onchange="assetInfo.getBasicApplyBatchDetailListByType(this);"
                                                            class="form-control input-full">
                                                    </select>
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    单元
                                                </label>
                                                <div class="col-sm-2">
                                                    <select name="unit"
                                                            onchange="assetInfo.getBasicApplyBatchDetailListByType(this);"
                                                            class="form-control input-full"></select>
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    状态
                                                </label>
                                                <div class="col-sm-2">
                                                    <select name="status" class="form-control input-full">
                                                        <option value="">请选择</option>
                                                        <option value="runing">待清查</option>
                                                        <option value="finish">已清查</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline">
                                                <label class="col-sm-1 control-label">
                                                    权证号
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-maxlength="50" placeholder="权证号"
                                                           name="name"
                                                           class="form-control input-full">
                                                </div>
                                                <div class="col-sm-2 ">
                                                    <button type="button" class="btn btn-info btn-sm"
                                                            onclick="assetInfo.loadSurveyAssetInfoItemBaseList(this);">
                                                        <span class="btn-label"><i class="fa fa-search"></i></span>
                                                        查询
                                                    </button>
                                                    <button class="btn btn-warning btn-sm"
                                                            type="button" data-toggle="modal"
                                                            onclick="assetInfo.delSurveyAssetInfoItemByDeclareId() ;"><span
                                                            class="btn-label"><i class="fa fa-minus"></i></span>移除
                                                    </button>

                                                </div>
                                                <div class="col-sm-2">
                                                    <div class="btn-group" role="group"
                                                         aria-label="Button group with nested dropdown">

                                                        <button class="btn btn-success btn-sm"
                                                                type="button" data-toggle="modal"
                                                                onclick="assetInfo.addSurveyAssetInfoGroup()"><span
                                                                class="btn-label"><i class="fa fa-plus"></i></span>添加清查组
                                                        </button>
                                                        <div class="btn-group" role="group">
                                                            <button id="btnGroupDrop1" type="button"
                                                                    class="btn btn-secondary dropdown-toggle btn-sm"
                                                                    data-toggle="dropdown" aria-haspopup="true"
                                                                    aria-expanded="false">
                                                                添加权证到组中
                                                            </button>
                                                            <div class="dropdown-menu" aria-labelledby="btnGroupDrop1"
                                                                 data-name="surveyAssetInfoGroup">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-2">
                                                    <button type="button" class="btn btn-info btn-sm"
                                                            onclick="assetInfo.taskCopy(this) ;"><i
                                                            class="fa fa-copy" aria-hidden="true"></i> 拷贝
                                                    </button>
                                                    <button type="button" class="btn btn-warning btn-sm"
                                                            onclick="assetInfo.taskPaste(this) ;"><i
                                                            class="fa fa-clipboard" aria-hidden="true"></i>粘贴
                                                    </button>
                                                </div>
                                                <div class="col-sm-2">
                                                    <div class="input-group ">
                                                        <input type="hidden" name="copyId">
                                                        <input type="text" readonly="readonly" name="copyName"
                                                               class="form-control form-control-sm"
                                                               placeholder="无拷贝数据">
                                                        <div class="input-group-prepend ">
                                                            <button class="btn btn-warning btn-sm"
                                                                    style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                                    type="button"
                                                                    onclick="$(this).closest('.input-group').find('input').val('');">
                                                                <i class="fa "></i>
                                                                清空
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline">
                                                <div class="col-sm-2 ">

                                                </div>
                                                <div class="col-sm-2 ">

                                                </div>
                                                <div class="col-sm-1 ">

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <table class="table table-bordered" id="tb_infoBaseItem_list"></table>
                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        资产清查组列表
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
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline">
                                                <label class="col-sm-1 control-label">
                                                    组名称
                                                </label>
                                                <div class="col-sm-2">
                                                    <input type="text" data-rule-maxlength="50" placeholder="组名称"
                                                           name="groupName"
                                                           class="form-control input-full">
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    状态
                                                </label>
                                                <div class="col-sm-2">
                                                    <select name="status" class="form-control input-full">
                                                        <option value="">请选择</option>
                                                        <option value="runing">待清查</option>
                                                        <option value="finish">已清查</option>
                                                    </select>
                                                </div>
                                                <div class="col-sm-3">
                                                    <button type="button" class="btn btn-info btn-sm"
                                                            onclick="assetInfo.loadSurveyAssetInfoGroupList(this);">
                                                        <span class="btn-label"><i class="fa fa-search"></i></span>
                                                        查询
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <table class="table table-bordered" id="tbSurveyAssetInfoGroupList"></table>
                                </form>
                            </div>
                        </div>
                    </div>
                    <%@include file="/views/share/form_apply.jsp" %>
                    <%--<%@include file="/views/share/form_log.jsp" %>--%>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>

<script>

    var assetInfo = {};

    assetInfo.run = function (data, url, type, callback, funParams, errorCallback) {
        Loading.progressShow();
        $.ajax({
            type: type,
            url: '${pageContext.request.contextPath}' + url,
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    if (funParams) {
                        if (funParams == 'save') {
                            notifySuccess("成功", "保存数据成功!");
                        }
                        if (funParams == 'add') {
                            notifySuccess("成功", "添加数据成功!");
                        }
                        if (funParams == 'update') {
                            notifySuccess("成功", "修改数据成功!");
                        }
                        if (funParams == 'query') {
                            notifySuccess("成功", "查询数据成功!");
                        }
                        if (funParams == 'delete') {
                            notifySuccess("成功", "删除数据成功!");
                        }
                    }
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    if (result.errmsg) {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                    } else {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                    }
                    if (errorCallback) {
                        errorCallback();
                    }
                }
            },
            error: function (result) {
                Loading.progressHide();
                if (result.errmsg) {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                } else {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                }
            }
        });
    };
    assetInfo.ajaxServerFun = function (data, url, type, callback, funParams, errorCallback) {
        var deleteParams = false;
        if (funParams) {
            if (funParams == 'delete') {
                deleteParams = true;
            }
        }
        if (deleteParams) {
            AlertConfirm("是否确认删除当前数据", "删除相应的数据后将不可恢复", function (flag) {
                assetInfo.run(data, url, type, callback, funParams, errorCallback);
            });
        } else {
            assetInfo.run(data, url, type, callback, funParams, errorCallback);
        }
    };

    assetInfo.ajaxServerMethod = function (data, url, type, callback, errorCallback) {
        assetInfo.ajaxServerFun(data, url, type, callback, null, errorCallback);
    };

    /**
     * 申报记录 修改
     * @param data
     * @param callback
     */
    assetInfo.saveDeclareRecord = function (data, callback) {
        assetInfo.ajaxServerFun({formData: JSON.stringify(data)}, "/declareRecord/saveDeclareRecord", "post", callback, null);
    };
    /**
     * 申报记录 get
     * @param data
     * @param callback
     */
    assetInfo.getDeclareRecordListByIds = function (data, callback) {
        assetInfo.ajaxServerFun({id: data.join(",")}, "/declareRecord/getDeclareRecordListByIds", "get", callback, null);
    };
    assetInfo.saveDeclareRecordArray = function (data, updateNull, callback) {
        if (updateNull == null || updateNull == undefined || updateNull == '') {
            updateNull = false;
        }
        assetInfo.ajaxServerFun({
            formData: JSON.stringify(data),
            updateNull: updateNull
        }, "/declareRecord/saveDeclareRecordArray", "post", callback, null);
    };

    /*
     资产清查 具体的数据保存
     */
    assetInfo.saveSurveyAssetInventory = function (data, callback) {
        assetInfo.ajaxServerFun({formData: JSON.stringify(data)}, "/surveyAssetInventory/saveSurveyAssetInventory", "post", callback, null);
    };

    /**
     * 资产清查组
     * @param data
     * @param callback
     */
    assetInfo.saveAndUpdateSurveyAssetInfoGroup = function (data, callback) {
        assetInfo.ajaxServerFun({formData: JSON.stringify(data)}, "/surveyAssetInfoGroup/saveAndUpdateSurveyAssetInfoGroup", "post", callback);
    };

    /**
     * 资产清查组 删除
     * @param id
     * @param callback
     */
    assetInfo.deleteSurveyAssetInfoGroupById = function (id, callback) {
        assetInfo.ajaxServerFun({id: id}, "/surveyAssetInfoGroup/deleteSurveyAssetInfoGroupById", "post", callback, "delete")
    };

    /**
     * 资产清查组 获取
     * @param id
     * @param callback
     */
    assetInfo.getSurveyAssetInfoGroupById = function (id, callback) {
        assetInfo.ajaxServerFun({id: id}, "/surveyAssetInfoGroup/getSurveyAssetInfoGroupById", "get", callback);
    };

    /**
     * 资产清查 save 单个
     * @param data
     * @param callback
     */
    assetInfo.saveAndUpdateSurveyAssetInfoItem = function (data, callback, updateNull) {
        if (updateNull == null || updateNull == undefined) {
            updateNull = false;
        }
        assetInfo.ajaxServerFun({
            formData: JSON.stringify(data),
            updateNull: updateNull
        }, "/surveyAssetInfoItem/saveAndUpdateSurveyAssetInfoItem", "post", callback);
    };

    /**
     * 资产清查 save 多个
     * @param data
     * @param callback
     */
    assetInfo.addSurveyAssetInfoItem = function (data, callback) {
        assetInfo.ajaxServerFun({formData: JSON.stringify(data)}, "/surveyAssetInfoItem/addSurveyAssetInfoItem", "post", callback);
    };
    /**
     * 资产清查 save 多个
     * @param data
     * @param callback
     */
    assetInfo.addSurveyAssetInfoItemRecordData = function (data, callback) {
        assetInfo.ajaxServerFun({formData: JSON.stringify(data)}, "/surveyAssetInfoItem/addSurveyAssetInfoItemRecordData", "post", callback);
    };

    /**
     * 资产清查 delete
     * @param id
     * @param callback
     */
    assetInfo.deleteSurveyAssetInfoItemById = function (id, callback) {
        assetInfo.ajaxServerFun({id: id}, "/surveyAssetInfoItem/deleteSurveyAssetInfoItemById", "post", callback, "delete");
    };


    /**
     * 资产清查 get
     * @param id
     * @param callback
     */
    assetInfo.getSurveyAssetInfoItemById = function (id, callback) {
        assetInfo.ajaxServerFun({id: id}, "/surveyAssetInfoItem/getSurveyAssetInfoItemById", "get", callback);
    };

    assetInfo.getSurveyAssetInfoItemIdsByGroupId = function (groupId, callback) {
        assetInfo.ajaxServerFun({groupId: groupId}, "/surveyAssetInfoItem/getSurveyAssetInfoItemIdsByGroupId", "get", callback);
    };

    /**
     * 资产清查 查询
     * @param data
     * @param callback
     */
    assetInfo.getSurveyAssetInfoItemListByQuery = function (data, callback) {
        assetInfo.ajaxServerFun(data, "/surveyAssetInfoItem/getSurveyAssetInfoItemListByQuery", "get", callback)
    };

    /**
     *  资产清查 业务数据粘贴
     */
    assetInfo.parseSurveyAssetInventory = function (inventoryId, type, masterId, callback) {
        assetInfo.ajaxServerFun({}, "/surveyAssetInventory/parseSurveyAssetInventory/" + inventoryId + "/" + type + "/" + masterId, "post", callback)
    };

    assetInfo.declareRecordTable = $("#tb_declare_record_list");
    assetInfo.groupTable = $("#tbSurveyAssetInfoGroupList");
    assetInfo.groupBox = $("#boxSurveyAssetInfoGroup");
    assetInfo.InfoItemTable = $("#tb_infoItem_list");
    assetInfo.InfoItemBaseTable = $("#tb_infoBaseItem_list");
    assetInfo.queryForm = $("#queryForm");

    assetInfo.handleJquery = function (obj) {
        if (obj instanceof jQuery) {
            return obj;
        } else {
            return $(obj.selector);
        }
    };

    assetInfo.loadDeclareRecordList = function (_this) {
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
        var target = assetInfo.handleJquery(assetInfo.declareRecordTable);
        var selector = target.selector;
        var cols = [];
        cols.push({checkbox: true, width: "5%", title: "勾选"});
        cols.push({
            field: 'name', title: '权证号', width: "13%", formatter: function (value, row, index) {
                return value;
            }
        });
        cols.push({field: 'seat', title: '坐落', width: "20%"});
        cols.push({field: 'certUse', title: '证载用途', width: "5%"});
        cols.push({field: 'practicalUse', title: '实际用途', width: "5%"});
        cols.push({field: 'floorArea', title: '面积', width: "5%"});
        cols.push({
            field: 'inventoryStatus', title: '状态', width: "10%", formatter: function (value, row, index) {
                var html = "";
                if (value) {
                    if (value == 'runing') {
                        html += "<span class='label label-info'>";
                        html += "待清查";
                        html += "</span>";
                    }
                    if (value == 'finish') {
                        html += "<span class='label label-success'>";
                        html += "已清查";
                        html += "</span>";
                    }
                } else {
                    html += "<span class='label label-warning'>";
                    html += "待认领";
                    html += "</span>";
                }
                return html;
            }
        });
        target.bootstrapTable('destroy');
        TableInit(target, "${pageContext.request.contextPath}/declareRecord/getDeclareRecordList", cols, data, {
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

    assetInfo.loadSurveyAssetInfoGroupList = function (_this) {
        var data = {projectId: '${projectInfo.id}', assetInfoId: '${surveyAssetInfo.id}'};
        if (_this) {
            data = formSerializeArray($(_this).closest("form"));
        }
        var arr = Object.keys(data);
        $.each(arr, function (i, item) {
            if (!data[item]) {
                data[item] = undefined;
            }
        });
        var target = assetInfo.handleJquery(assetInfo.groupTable);
        var cols = [];
        cols.push({field: 'groupName', title: '组名称', width: "30%"});
        cols.push({
            field: 'status', title: '状态', width: "10%", formatter: function (value, row, index) {
                var str = "";
                if (value) {
                    if (value == 'runing') {
                        str += "<span class='label label-info'>";
                        str += "待清查";
                        str += "</span>";
                    }
                    if (value == 'finish') {
                        str += "<span class='label label-success'>";
                        str += "已清查";
                        str += "</span>";
                    }
                }
                return str;
            }
        });
        cols.push({
            field: 'id', title: '操作', width: "30%", formatter: function (value, row, index) {
                var str = "";
                str += '<button type="button" onclick="assetInfo.groupHandel(' + row.id + ')" style="margin-left: 5px;" class="btn   btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="清查操作">';
                str += '<i class="fa fa-cog"></i>';
                str += '</button>';

                str += '<button type="button" onclick="assetInfo.editSurveyAssetInfoGroup(' + row.id + ')"  style="margin-left: 5px;"  class="btn   btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                str += '<i class="fa fa-pen"></i>';
                str += '</button>';

                str += '<button type="button" onclick="assetInfo.delSurveyAssetInfoGroup(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn   btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';


                return str;
            }
        });
        target.bootstrapTable('destroy');
        TableInit(target, "${pageContext.request.contextPath}/surveyAssetInfoGroup/getBootstrapTableVo", cols, data, {
            method: "get",
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
                assetInfo.writeQuerySurveyAssetInfoGroupHtml();
                var item = target.bootstrapTable('getData');
                if (item.length >= 1) {
                    target.closest(".card").parent().show();
                } else {
                    target.closest(".card").parent().hide();
                }
            }
        });
    };

    assetInfo.loadSurveyAssetInfoItemBaseList = function (_this) {
        var options = {
            projectId: '${projectInfo.id}',
            assetInfoId:${surveyAssetInfo.id}
        };
        var data = {};
        if (_this) {
            data = formSerializeArray($(_this).closest("form"));
        }
        jQuery.extend(data, options);
        var arr = Object.keys(data);
        $.each(arr, function (i, item) {
            if (!data[item]) {
                data[item] = undefined;
            }
        });
        var target = assetInfo.handleJquery(assetInfo.InfoItemBaseTable);
        var selector = target.selector;
        var cols = [];
        cols.push({checkbox: true, width: "5%", title: "勾选"});
        cols.push({
            field: 'name', title: '权证号', width: "33%", formatter: function (value, row, index) {
                var str = value;
                if (row.groupId) {
                    str += "<span class='label label-info'>";
                    str += row.groupName;
                    str += "</span>";
                }
                return str;
            }
        });
        cols.push({
            field: 'status', title: '状态', width: "10%", formatter: function (value, row, index) {
                var str = "";
                if (value) {
                    if (value == 'runing') {
                        str += "<span class='label label-info'>";
                        str += "待清查";
                        str += "</span>";
                    }
                    if (value == 'finish') {
                        str += "<span class='label label-success'>";
                        str += "已清查";
                        str += "</span>";
                    }
                }
                return str;
            }
        });
        cols.push({
            field: 'id', title: '操作', width: "40%", formatter: function (value, row, index) {
                var str = "";
                if (!row.groupId) {
                    str += '<button type="button" onclick="assetInfo.itemHandel(' + value + ')" style="margin-left: 5px;" class="btn   btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="清查操作">';
                    str += '<i class="fa fa-cog"></i>';
                    str += '</button>';
                }

                return str;
            }
        });
        target.bootstrapTable('destroy');
        TableInit(target, "${pageContext.request.contextPath}/surveyAssetInfoItem/getBootstrapTableVo", cols, data, {
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

    //认领权证
    assetInfo.selectClaimData = function () {
        var target = assetInfo.handleJquery(assetInfo.declareRecordTable);
        var rows = target.bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            notifyWarning("勾选的数据为0", "请勾选需要认领的权证!");
        } else {
            var idArray = [];
            var num = 0;
            rows.forEach(function (ele, index) {
                if (ele.inventoryStatus) {
                    if (ele.inventoryStatus == 'finish' || ele.inventoryStatus == 'runing') {
                        num++;
                    }
                } else {
                    idArray.push(ele.id);
                }
            });
            if (num != 0) {
                notifyWarning("警告", "此次选择的权证存在已经被清查的数据或者已经被认领过的数据,请检查!");
                target.bootstrapTable('uncheckAll');
                return false;
            }
            if (idArray.length >= 1) {
                AlertConfirm("确认认领这些权证", "确定", function (flag) {
                    var data = [];
                    $.each(idArray, function (i, declareId) {
                        var obj = assetInfo.handleJquery(assetInfo.declareRecordTable).bootstrapTable('getRowByUniqueId', declareId);
                        var item = {
                            name: obj.name,
                            declareId: declareId,
                            assetInfoId: '${surveyAssetInfo.id}',
                            status: 'runing',
                            groupId: 0
                        };
                        data.push(item);
                    });
                    assetInfo.addSurveyAssetInfoItemRecordData(data, function (result) {
                        AlertSuccess("添加成功", "本次认领了" + result + "条数据");
                        assetInfo.loadDeclareRecordList();
                        assetInfo.loadSurveyAssetInfoItemBaseList();
                    });
                });
            } else {
                target.bootstrapTable('uncheckAll');
                notifyWarning("警告", "此次选择的权证都已经被清查了!");
            }
        }
    };

    /***
     * 拷贝清查具体数据
     */
    assetInfo.taskCopy = function (_this) {
        var frm = $(_this).closest("form");
        var target = assetInfo.handleJquery(assetInfo.InfoItemBaseTable);
        var rows = target.bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            notifyWarning("提示", "请勾选认领的权证中的一个!");
        } else {
            if (rows.length > 1) {
                notifyWarning("提示", "只能拷贝一个!");
                target.bootstrapTable('uncheckAll');
                return false;
            }
            var data = rows[0];
            if (data.groupId) {
                notifyWarning("提示", "此数据属于清查组不能拷贝!");
                target.bootstrapTable('uncheckAll');
                return false;
            }
            if (!data.inventoryId) {
                notifyWarning("提示", "此数据没有完成清查业务 不能拷贝!");
                target.bootstrapTable('uncheckAll');
                return false;
            }
            frm.find("[name=copyId]").val(data.id);
            frm.find("[name=copyName]").val(data.name);
            target.bootstrapTable('uncheckAll');
            notifySuccess("成功", "拷贝成功!");
        }
    };

    /***
     * 粘贴清查具体数据
     */
    assetInfo.taskPaste = function (_this) {
        var frm = $(_this).closest("form");
        var target = assetInfo.handleJquery(assetInfo.InfoItemBaseTable);
        var rows = target.bootstrapTable('getSelections');
        var id = frm.find("[name=copyId]").val();
        if (!id) {
            notifyInfo('提示', '没有拷贝对象');
            return false;
        }
        if (!rows || rows.length <= 0) {
            notifyWarning("提示", "请需要粘贴列表中的一个或者多个!");
        } else {
            var idArray = [];
            rows.forEach(function (ele, index) {
                idArray.push(ele.id);
            });
            //过滤一次
            var filtered = idArray.filter(function (element, index, array) {
                return element == id;
            });
            //判断
            if (filtered.length == 1) {
                notifyWarning("警告", "需要粘贴的从数据包含了自身,这样情况是不被允许的!");
                target.bootstrapTable('uncheckAll');
                return false;
            }
            assetInfo.getSurveyAssetInfoItemById(id, function (data) {
                AlertConfirm("粘贴操作", "是否确认", function (flag) {
                    assetInfo.parseSurveyAssetInventory(data.inventoryId, 'unit', idArray.join(","), function () {
                        notifySuccess("成功", "粘贴完成");
                        assetInfo.loadSurveyAssetInfoItemBaseList();
                    });
                });
            });
        }
    };

    /*
     资产清查组  清查数据
     */
    assetInfo.groupHandel = function (id) {
        var inventoryId = 0;
        var obj = assetInfo.handleJquery(assetInfo.groupTable).bootstrapTable('getRowByUniqueId', id);
        if (obj.inventoryId) {
            inventoryId = obj.inventoryId;
        }
        assetInfo.getSurveyAssetInfoItemListByQuery({groupId: id}, function (data) {
            if (data.length >= 1) {
                var item = data[0];
                assetInfo.surveyAssetInventoryHandle(inventoryId, item.declareId, "group", id, obj.groupName);
            } else {
                notifyInfo('提示', '没有在组中添加认领的权证');
            }
        });
    };

    /**
     * 单个权证   清查数据
     */
    assetInfo.itemHandel = function (id) {
        var inventoryId = 0;
        var item = assetInfo.handleJquery(assetInfo.InfoItemBaseTable).bootstrapTable('getRowByUniqueId', id);
        if (item.inventoryId) {
            inventoryId = item.inventoryId;
        }
        if (item.groupId) {
            AlertError("已经被添加到组中不可作为单独事项去清查了", "假如作为单独事项来做清查业务  那么请从组中移除添加的这个认领权证");
        } else {
            assetInfo.surveyAssetInventoryHandle(inventoryId, item.declareId, "unit", item.id, item.name);
            //验证权证是否填写了查勘，可清查
            <%--$.ajax({--%>
            <%--    url: '${pageContext.request.contextPath}/surveyAssetInventory/canInventory',--%>
            <%--    type: 'post',--%>
            <%--    data: {assetInfoItemId: id},--%>
            <%--    dataType: 'json',--%>
            <%--    success: function (result) {--%>
            <%--        if (result.ret && result.data) {--%>
            <%--            assetInfo.surveyAssetInventoryHandle(inventoryId, item.declareId, "unit", item.id, item.name);--%>
            <%--        } else {--%>
            <%--            notifyInfo("提示", "请先填写查勘信息后，才可进行清查");--%>
            <%--        }--%>
            <%--    }--%>
            <%--})--%>
        }
    };

    /**
     * 资产清查 进入 具体的业务页面
     * @param inventoryId
     * @param declareId
     * @param type
     * @param masterId
     */
    assetInfo.surveyAssetInventoryHandle = function (inventoryId, declareId, type, masterId, masterName) {
        var frame = layer.open({
            type: 2,
            title: '',
            shadeClose: true,
            shade: true,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: '${pageContext.request.contextPath}/surveyAssetInventory/view/${projectPlanDetails.projectId}/${projectPlanDetails.id}/' + inventoryId + "/" + declareId + "/" + masterName,
            cancel: function (index, layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
                //放弃按钮 不需要做处理
            },
            btnAlign: 'c',
            btn: ['保存', '关闭'],
            yes: function (index, layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
                iframe.newGetFormData(function (data) {
                    assetInfo.saveSurveyAssetInventory(data, function (result) {
                        //以组的方式清查
                        if (type == 'group') {
                            assetInfo.getSurveyAssetInfoGroupById(masterId, function (obj) {
                                obj.inventoryId = result.surveyAssetInventory.id;
                                obj.status = 'finish';

                                assetInfo.saveAndUpdateSurveyAssetInfoGroup(obj, function () {
                                    assetInfo.loadSurveyAssetInfoGroupList();
                                });
                            });
                            assetInfo.getSurveyAssetInfoItemListByQuery({
                                groupId: masterId,
                                assetInfoId: '${surveyAssetInfo.id}'
                            }, function (data) {
                                var objArr = [];
                                $.each(data, function (i, obj) {
                                    obj.status = 'finish';
                                    objArr.push(obj);
                                });
                                if (objArr.length >= 1) {
                                    assetInfo.addSurveyAssetInfoItem(objArr, function () {
                                        assetInfo.loadSurveyAssetInfoItemBaseList();
                                    });
                                }
                            });
                        }
                        //单个的方式清查
                        if (type == 'unit') {
                            assetInfo.getSurveyAssetInfoItemById(masterId, function (obj) {
                                obj.inventoryId = result.surveyAssetInventory.id;
                                obj.status = 'finish';
                                assetInfo.saveAndUpdateSurveyAssetInfoItem(obj, function () {
                                    assetInfo.loadSurveyAssetInfoItemBaseList();
                                });
                            })
                        }
                        layer.closeAll('iframe');
                        notifyInfo("成功", "清查业务数据填写成功");
                    });
                });

            },
            btn2: function (index, layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
                //关闭按钮 不需要做处理
            }
        });
        layer.full(frame);
    };

    // 资产清查组 show
    assetInfo.addSurveyAssetInfoGroup = function () {
        var groupName = "组" + (assetInfo.handleJquery(assetInfo.groupTable).bootstrapTable('getData').length + 1);
        var data = {assetInfoId: '${surveyAssetInfo.id}'};
        data.groupName = groupName;
        data.status = 'runing';
        assetInfo.saveAndUpdateSurveyAssetInfoGroup(data, function (item) {
            notifySuccess("成功", "添加资产清查组成功!");
            assetInfo.loadSurveyAssetInfoGroupList();
        });
    };

    /**
     *添加权证到组中
     */
    assetInfo.selectSurveyAssetInfoGroup = function (groupId) {
        if (!groupId) {
            notifyWarning("无清查组", "请添加清查组");
            return false;
        }
        var groupData = assetInfo.handleJquery(assetInfo.groupTable).bootstrapTable('getRowByUniqueId', groupId);
        var target = assetInfo.handleJquery(assetInfo.InfoItemBaseTable);
        var rows = target.bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            notifyWarning("请勾选认领的权证列表", "勾选的将添加到所选择的清查组中!");
        } else {
            var data = [];
            var arr = [];
            var num = 0;
            rows.forEach(function (ele, index) {
                ele.groupId = groupData.id;
                ele.groupName = groupData.groupName;
                data.push(ele);
                if (ele.status == 'finish') {
                    num++;
                }
                arr.push(ele.id);
            });
            if (num != 0) {
                notifyWarning("警告", "添加到清查组的认领权证有已经完成清查任务的认领权证,请检查!");
                target.bootstrapTable('uncheckAll');
                return false;
            }
            assetInfo.getSurveyAssetInfoItemIdsByGroupId(groupId, function (ids) {
                var j = 0;
                if (ids.length >= 1) {
                    for (var i = 0; i < ids.length; i++) {
                        var ele = ids[i];
                        if (jQuery.inArray(ele, arr) != -1) {
                            j++;
                        }
                    }
                }
                if (j != 0) {
                    AlertError(groupData.groupName + "中已经包含勾选的列表中的元素中一个或者多个", "请修改后再次操作");
                    return false;
                }
                AlertConfirm("是否确认添加当前选中数据到清查组中", "确定", function (flag) {
                    assetInfo.addSurveyAssetInfoItem(data, function () {
                        AlertSuccess("添加成功", "已经添加到 《" + groupData.groupName + "》 中");
                        target.bootstrapTable('uncheckAll');
                        assetInfo.loadSurveyAssetInfoItemBaseList();
                    });
                });
            });
        }
    };

    /**
     * 资产清查组 编辑
     * @param id
     */
    assetInfo.editSurveyAssetInfoGroup = function (id) {
        var target = assetInfo.handleJquery(assetInfo.groupBox);
        var frm = target.find("form");
        frm.clearAll();
        var data = assetInfo.handleJquery(assetInfo.groupTable).bootstrapTable('getRowByUniqueId', id);
        frm.initForm(data);
        var toolBar = "." + target.attr("id");
        assetInfo.loadSurveyAssetInfoItemList({groupId: id}, assetInfo.handleJquery(assetInfo.InfoItemTable), toolBar);
        target.modal("show");
    };

    //资产清查组 add
    assetInfo.saveSurveyAssetInfoGroup = function () {
        var target = assetInfo.handleJquery(assetInfo.groupBox);
        var frm = target.find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        data.assetInfoId = '${surveyAssetInfo.id}';
        assetInfo.saveAndUpdateSurveyAssetInfoGroup(data, function (item) {
            target.modal("hide");
            notifySuccess("成功", "修改资产清查组成功!");
            assetInfo.loadSurveyAssetInfoGroupList();
        });
    };

    //资产清查组 delete
    assetInfo.delSurveyAssetInfoGroup = function (id) {
        assetInfo.getSurveyAssetInfoItemIdsByGroupId(id, function (data) {
            if (data.length >= 1) {
                notifyWarning("提示", "请先移除组中的数据!");
                return false;
            }
            assetInfo.deleteSurveyAssetInfoGroupById(id, function () {
                assetInfo.loadSurveyAssetInfoGroupList();
            })
        });
    };

    /**
     * 在查询那写入下拉框里面的html
     */
    assetInfo.writeQuerySurveyAssetInfoGroupHtml = function () {
        var dataAll = assetInfo.handleJquery(assetInfo.groupTable).bootstrapTable('getData');
        var frm = assetInfo.handleJquery(assetInfo.queryForm);
        var html = "";
        if (dataAll.length == 0) {
            html += "<a href='javascript://' class='dropdown-item'> 请选择组</a>";
        }
        $.each(dataAll, function (i, item) {
            html += "<a href='javascript://' class='dropdown-item' onclick='assetInfo.selectSurveyAssetInfoGroup(" + item.id + ")'" + ">" + item.groupName + "</a>";
        });
        frm.find("div[data-name='surveyAssetInfoGroup']").empty().append(html);
    };

    assetInfo.loadSurveyAssetInfoItemList = function (query, table, toolbar) {
        var cols = [];
        var selector = table.selector;
        cols.push({field: 'name', title: '名称', width: "70%"});
        table.bootstrapTable('destroy');
        TableInit(table, "${pageContext.request.contextPath}/surveyAssetInfoItem/getBootstrapTableVo", cols, query, {
            method: "get",
            showColumns: false,
            showRefresh: false,
            toolbar: toolbar,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        }, true);
    };


    /**
     * 移除组中添加的数据
     */
    assetInfo.removeGroupSurveyAssetInfoItem = function () {
        var target = assetInfo.handleJquery(assetInfo.InfoItemTable);
        var rows = target.bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            notifyWarning("提示", "请勾选需要移除的数据!");
            return false;
        }
        var arr = [];
        var declareIds = [];
        rows.forEach(function (data, index) {
            data.groupId = 0;
            data.inventoryId = 0;
            data.status = 'runing';
            data.groupName = '';
            arr.push(data);
            declareIds.push(data.declareId);
        });
        assetInfo.getDeclareRecordListByIds(declareIds, function (data) {
            var arrRecord = $.grep(data, function (n, i) {
                return n.inventoryStatus == "finish";
            });
            if (arrRecord.length != 0) {
                notifyWarning("提示", "勾选的数据有已经完成清查的数据,完成清查后不能在删除了");
                return false;
            }
            assetInfo.addSurveyAssetInfoItem(arr, function () {
                notifySuccess("成功", "移除成功!");
                target.bootstrapTable('refresh');
                assetInfo.handleJquery(assetInfo.InfoItemBaseTable).bootstrapTable('refresh');
            });
        });
    };

    assetInfo.delSurveyAssetInfoItemByDeclareId = function () {
        var target = assetInfo.handleJquery(assetInfo.InfoItemBaseTable);
        var rows = target.bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            notifyWarning("提示", "请勾选需要删除的数据!");
            return false;
        }
        var arr = [];
        var declareIds = [];
        var ids = [];

        rows.forEach(function (ele, index) {
            if (ele.groupId) {
                arr.push(ele);
            }
            ids.push(ele.id);
            declareIds.push(ele.declareId);
        });
        if (arr.length != 0) {
            notifyWarning("勾选的数据有已经加入组中", "请进入组中移除,然后在进入认领列表中删除");
            return false;
        }
        assetInfo.getDeclareRecordListByIds(declareIds, function (data) {
            var arrRecord = $.grep(data, function (n, i) {
                return n.inventoryStatus == "finish";
            });
            if (arrRecord.length != 0) {
                notifyWarning("提示", "勾选的数据有已经完成清查的数据,完成清查后不能在删除了,请检查");
                return false;
            }
            assetInfo.deleteSurveyAssetInfoItemById(ids.join(","), function () {
                target.bootstrapTable('refresh');
                assetInfo.handleJquery(assetInfo.declareRecordTable).bootstrapTable('refresh');
            });
        });
    };

    assetInfo.getBasicApplyBatchDetailListByType = function (_this) {
        var childControl = $(_this).closest('form').find('[name=' + $(_this).attr('data-for-name') + ']');
        var data = {};
        var type = $(_this).attr('data-child-type');
        if ('unit' == type) {
            data.pid = $(_this).val();
        }
        var basicApplyBatchId = $(_this).find("option:selected").attr('data-apply-batch-id');
        data.type = type;
        data.basicApplyBatchId = basicApplyBatchId;
        $.ajax({
            url: '${pageContext.request.contextPath}/basicApplyBatch/getBasicApplyBatchDetailListByType',
            type: 'post',
            data: data,
            dataType: 'json',
            success: function (result) {
                if (result.ret && result.data) {
                    var html = '<option value="">请选择</option>';
                    $.each(result.data, function (i, item) {
                        html += '<option value="' + item.id + '" data-apply-batch-id="' + item.applyBatchId + '">' + item.name + '</option>';
                    })
                    childControl.empty().append(html);
                }
            }
        })
    }


    $(document).ready(function () {
        assetInfo.loadDeclareRecordList();
        assetInfo.loadSurveyAssetInfoGroupList();
        assetInfo.loadSurveyAssetInfoItemBaseList();
    });

</script>


<script type="text/javascript">


    /**
     * 提交
     * @param mustUseBox
     * @returns {boolean}
     */
    function submit(mustUseBox) {
//不要这样get获取数据  今天发现 getData 和 getRowByUniqueId 只能获取在页面上显示出来的数据,假如你的分页是10条,那么数据一定在10条以内,显然这样是错误的
//        var dataAll = assetInfo.handleJquery(assetInfo.InfoItemBaseTable).bootstrapTable('getData');
        var query = {
            assetInfoId: '${surveyAssetInfo.id}',
            status: 'runing',
            creator: '${projectPlanDetails.executeUserAccount}'
        };
        assetInfo.getSurveyAssetInfoItemListByQuery(query, function (filterData) {
            if (filterData.length != 0) {
                notifyWarning("提示", "认领的权证有未清查的!");
                return false;
            }
            var data = {
                id: '${surveyAssetInfo.id}',
                planDetailId: '${surveyAssetInfo.planDetailId}',
                projectId: '${surveyAssetInfo.projectId}',
                processInsId: '${surveyAssetInfo.processInsId}'
            };
            var formData = JSON.stringify(data);
            if ("${processInsId}" != "0") {
                submitEditToServer(formData);
            } else {
                submitToServer(formData, mustUseBox);
            }
        });

    }


</script>


</html>

