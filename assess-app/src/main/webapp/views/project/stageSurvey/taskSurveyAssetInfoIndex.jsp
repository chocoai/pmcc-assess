<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
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
                                                                class="fa fa-plus"></i></span>认领
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
                                                <div class="col-sm-4 ">
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
                                                    <button class="btn btn-primary btn-sm"
                                                            type="button" data-toggle="modal"
                                                            onclick="assetInfo.checkUniformityBatch() ;"><span
                                                            class="btn-label"><i class="fa fa-bars"></i></span>一致性清查
                                                    </button>
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
                                                    <button class="btn btn-success btn-sm" style="margin-left: 5px;"
                                                            type="button"
                                                            onclick="assetInfo.showSurveyAssetInfoGroup();">
                                                        <span class="btn-label"><i
                                                                class="fa fa-plus"></i></span>添加
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
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>
<%--清查组管理--%>
<div id="boxSurveyAssetInfoGroup" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 50%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">添加清查组</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-check">
                        <label class="form-check-label">
                            <input class="form-check-input" type="checkbox" checked="checked" value="viewSpilt">
                            <span class="form-check-sign">权证与分割</span>
                        </label>
                    </div>
                    <div class="form-check">
                        <label class="form-check-label">
                            <input class="form-check-input" type="checkbox" checked="checked" value="taxArrears">
                            <span class="form-check-sign">税费欠款调查</span>
                        </label>
                    </div>
                    <div class="form-check">
                        <label class="form-check-label">
                            <input class="form-check-input" type="checkbox" checked="checked" value="damage">
                            <span class="form-check-sign">损坏调查</span>
                        </label>
                    </div>
                    <div class="form-check">
                        <label class="form-check-label">
                            <input class="form-check-input" type="checkbox" checked="checked" value="transfer">
                            <span class="form-check-sign">转让限制</span>
                        </label>
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
<%--清查组权证--%>
<div id="boxGroupInfoItemModal" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 50%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">清查组权证</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <button type="button" class="btn btn-success  btn-sm"
                        onclick="assetInfo.showClaimInfoItemModal() ;">
                    <i class="fa fa-check"></i>
                    选择
                </button>
                <button type="button" class="btn btn-warning  btn-sm"
                        onclick="assetInfo.removeGroupInfoItem() ;">
                    <i class="fa fa-minus"></i>
                    移除
                </button>
                <table class="table table-bordered" id="tbGroupInfoItemList"></table>
            </div>
        </div>
    </div>
</div>

<%--认领的权证--%>
<div id="boxClaimInfoItemModal" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 50%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">认领的权证</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <table class="table table-bordered" id="tbClaimInfoItemList"></table>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="assetInfo.selectClaimInfoItem();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<div id="boxEntityDamage" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" style="max-width: 50%;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">损坏调查</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="damageSurvey" class="form-horizontal">
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 col-form-label">
                                    区位是否损坏
                                </label>
                                <div class="col-sm-3">
                                    <select class="form-control input-full" id="rimIsNormal"
                                            name="rimIsNormal" required
                                            onchange="assetInfo.triggeEntityDamage()">
                                        <option value="">请选择</option>
                                        <option value="正常" selected="selected">正常</option>
                                        <option value="不正常">不正常</option>
                                    </select>
                                </div>
                                <label class="col-sm-1 col-form-label showZoneAdd" style="display:none">
                                    区位损坏新增
                                </label>
                                <div class="col-sm-3 showZoneAdd" style="display:none">
                                    <div class="btn btn-xs btn-success"
                                         onclick="assetInfo.entityDamageAppendHtml('zoneBit','zoneProject')">
                                        <i class="fa fa-plus"></i></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="zoneBit"></div>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <div class="form-inline x-valid">
                                <label class="col-sm-1 col-form-label">
                                    实物状况<br/>是否损坏
                                </label>
                                <div class="col-sm-3">
                                    <select class="form-control input-full" id="entityIsDamage"
                                            name="entityIsDamage" required
                                            onchange="assetInfo.triggeEntityDamage()">
                                        <option value="">请选择</option>
                                        <option value="损坏">损坏</option>
                                        <option value="未损坏" selected="selected">未损坏</option>
                                    </select>
                                </div>
                                <label class="col-sm-1 col-form-label showEntityAdd"
                                       style="display:none">
                                    实物损坏新增
                                </label>
                                <div class="col-sm-3 showEntityAdd" style="display:none">
                                    <div class="btn btn-xs btn-success"
                                         onclick="assetInfo.entityDamageAppendHtml('entity','entityProject')">
                                        <i class="fa fa-plus"></i></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="entity"></div>
                    <c:if test="${projectInfo.projectCategoryId == houseLand}">
                        <div class="row form-group">
                            <div class="col-md-12">
                                <div class="form-inline x-valid">
                                    <label class="col-sm-1 col-form-label">
                                        影响评估的其他事项
                                    </label>
                                    <div class="col-sm-3">
                                        <div class="btn btn-xs btn-success"
                                             onclick="assetInfo.entityDamageAppendHtml('otherProject','otherProject')">
                                            <i class="fa fa-plus"></i></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="otherProject"></div>
                    </c:if>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="assetInfo.saveEntityDamage();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
<script type="text/javascript">
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
    assetInfo.currGroupId = undefined;

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
                $(".tooltips").tooltip();//提示
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
        cols.push({field: 'groupName', title: '组名称', width: "50%"});
        cols.push({
            field: 'id', title: '操作', width: "30%", formatter: function (value, row, index) {
                var str = "";
                str += '<button type="button" onclick="assetInfo.showGroupInfoItemModal(' + row.id + ')" style="margin-left: 5px;" class="btn btn-info btn-xs tooltips"  data-placement="bottom" data-original-title="绑定权证">';
                str += '<i class="fa fa-address-card"></i>';
                str += '</button>';

                str += '<button type="button" onclick="assetInfo.surveyAssetInventoryGroup(' + row.id + ')"  style="margin-left: 5px;"  class="btn   btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="清查">';
                str += '<i class="fa fa-quidditch"></i>';
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
            field: 'bisFinishUniformity', title: '一致性清查', width: "10%", formatter: function (value, row, index) {
                var str = "";
                if (true == value) {
                    str += "<span class='label label-success'>";
                    str += "完成";
                    str += "</span>";
                } else {
                    str += "<span class='label label-info'>";
                    str += "未处理";
                    str += "</span>";
                }
                return str;
            }
        });
        cols.push({
            field: 'status', title: '状态', width: "10%", formatter: function (value, row, index) {
                var str = "";
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
                return str;
            }
        });
        cols.push({
            field: 'id', title: '操作', width: "10%", formatter: function (value, row, index) {
                var str = "";
                str += '<button type="button" onclick="assetInfo.uniformityInventoryHandle(' + value + ')" style="margin-left: 5px;" class="btn btn-primary btn-xs tooltips"  data-placement="bottom" data-original-title="一致性">';
                str += '<i class="fa fa-align-justify"></i>';
                str += '</button>';
                if (!row.groupId) {
                    str += '<button type="button" onclick="assetInfo.surveyAssetInventoryHandle(' + value + ')" style="margin-left: 5px;" class="btn btn-primary btn-xs tooltips"  data-placement="bottom" data-original-title="清查操作">';
                    str += '<i class="fa fa-quidditch"></i>';
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
     * 一致性清查
     * @param inventoryId
     * @param declareId
     * @param type
     * @param masterId
     */
    assetInfo.uniformityInventoryHandle = function (assetInfoItemId) {
        var frame = layer.open({
            type: 2,
            title: '',
            shadeClose: true,
            shade: true,
            maxmin: true, //开启最大化最小化按钮
            area: ['90%', '90%'],
            content: '${pageContext.request.contextPath}/surveyAssetInfoItem/inventory/' + assetInfoItemId,
            cancel: function (index, layero) {
                var iframe = window[layero.find('iframe')[0]['name']];//放弃按钮 不需要做处理
            },
            btnAlign: 'c',
            btn: ['保存', '关闭'],
            yes: function (index, layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
                iframe.newGetFormData(function (data) {
                    $.post('${pageContext.request.contextPath}/surveyAssetInfoItem/saveInventoryContent', {
                        formData: JSON.stringify(data)
                    }, function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "操作成功!");
                            layer.closeAll('iframe');
                            assetInfo.loadSurveyAssetInfoItemBaseList();
                        } else {
                            AlertError("操作失败，失败原因:" + result.errmsg);
                        }
                    })
                });
            },
            btn2: function (index, layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
            }
        });
    };

    /**
     * 资产清查 进入 具体的业务页面
     * @param inventoryId
     * @param declareId
     * @param type
     * @param masterId
     */
    assetInfo.surveyAssetInventoryHandle = function (assetInfoItemId) {
        var frame = layer.open({
            type: 2,
            title: '',
            shadeClose: true,
            shade: true,
            maxmin: true, //开启最大化最小化按钮
            area: ['90%', '90%'],
            content: '${pageContext.request.contextPath}/surveyAssetInventory/view/' + assetInfoItemId,
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
                        assetInfo.loadSurveyAssetInfoItemBaseList();
                        layer.closeAll('iframe');
                        notifyInfo("成功", "清查业务数据填写成功");
                    });
                });
            },
            btn2: function (index, layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
            }
        });
    };

    // 资产清查组 show
    assetInfo.showSurveyAssetInfoGroup = function () {
        $('#boxSurveyAssetInfoGroup').find(':checkbox').prop('checked', false);
        $('#boxSurveyAssetInfoGroup').modal();
    };

    //资产清查组 save
    assetInfo.saveSurveyAssetInfoGroup = function () {
        var formType = '';
        var groupName = '';
        $('#boxSurveyAssetInfoGroup').find(':checkbox:checked').each(function (i, item) {
            formType += $(item).val() + ',';
            groupName += "【" + $(item).closest('label').find('span').text() + "】";
        });
        $.post('${pageContext.request.contextPath}/surveyAssetInfoGroup/addSurveyAssetInfoGroup', {
            assetInfoId: '${surveyAssetInfo.id}',
            formType: formType.replace(/,$/, ''),
            groupName: groupName
        }, function (result) {
            if (result.ret) {
                notifySuccess("成功", "添加清查组成功!");
                $('#boxSurveyAssetInfoGroup').modal('hide');
                assetInfo.loadSurveyAssetInfoGroupList();
            } else {
                AlertError("保存数据失败，失败原因:" + result.errmsg);
            }
        })
    };

    //清查组 delete
    assetInfo.delSurveyAssetInfoGroup = function (id) {
        AlertConfirm("是否确认删除当前数据", "删除相应的数据后将不可恢复", function (flag) {
            $.post('${pageContext.request.contextPath}/surveyAssetInfoGroup/deleteSurveyAssetInfoGroupById', {
                id: id
            }, function (result) {
                if (result.ret) {
                    AlertSuccess("成功", "删除成功!");
                    assetInfo.loadSurveyAssetInfoGroupList();
                } else {
                    AlertError("操作失败，失败原因:" + result.errmsg);
                }
            })
        })
    };

    //显示清查组权证信息弹窗
    assetInfo.showGroupInfoItemModal = function (groupId) {
        assetInfo.loadGroupInfoItemList(groupId);
        assetInfo.currGroupId = groupId;
        $('#boxGroupInfoItemModal').modal();
    }

    //加载清查组中的权证信息
    assetInfo.loadGroupInfoItemList = function (groupId) {
        var cols = [];
        cols.push({field: 'name', title: '名称', width: "70%"});
        $('#tbGroupInfoItemList').bootstrapTable('destroy');
        TableInit($('#tbGroupInfoItemList'), "${pageContext.request.contextPath}/surveyAssetInfoItem/getBootstrapTableVo", cols, {
            groupId: groupId
        }, {
            method: "get",
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        }, true);
    }

    //显示认领的权证信息弹窗
    assetInfo.showClaimInfoItemModal = function () {
        assetInfo.loadClaimInfoItemList();
        $('#boxClaimInfoItemModal').modal();
    }

    //加载认领的权证信息
    assetInfo.loadClaimInfoItemList = function () {
        var cols = [];
        cols.push({field: 'name', title: '名称', width: "70%"});
        $('#tbClaimInfoItemList').bootstrapTable('destroy');
        TableInit($('#tbClaimInfoItemList'), "${pageContext.request.contextPath}/surveyAssetInfoItem/getBootstrapTableVo", cols, {
            assetInfoId: '${surveyAssetInfo.id}'
        }, {
            method: "get",
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        }, true);
    };

    //选择权证到清查组中
    assetInfo.selectClaimInfoItem = function () {
        var rows = $("#tbClaimInfoItemList").bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            notifyWarning("警告", "请选择数据!");
            return false;
        }
        var infoItems = '';
        $.each(rows, function (i, item) {
            infoItems += item.id + ',';
        })
        $.post('${pageContext.request.contextPath}/surveyAssetInfoGroup/selectClaimInfoItem', {
            assetInfoId: '${surveyAssetInfo.id}',
            infoItems: infoItems.replace(/,$/, ''),
            groupId: assetInfo.currGroupId
        }, function (result) {
            if (result.ret) {
                notifySuccess("成功", "选择成功!");
                $('#boxClaimInfoItemModal').modal('hide');
                assetInfo.loadGroupInfoItemList(assetInfo.currGroupId);
            } else {
                AlertError("操作失败，失败原因:" + result.errmsg);
            }
        })
    }

    /**
     * 移除组中添加的数据
     */
    assetInfo.removeGroupInfoItem = function () {
        var rows = $("#tbGroupInfoItemList").bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            notifyWarning("警告", "请选择数据!");
            return false;
        }
        var infoItems = '';
        $.each(rows, function (i, item) {
            infoItems += item.id + ',';
        })
        $.post('${pageContext.request.contextPath}/surveyAssetInfoGroup/removeGroupInfoItem', {
            infoItems: infoItems.replace(/,$/, '')
        }, function (result) {
            if (result.ret) {
                notifySuccess("成功", "移除成功!");
                assetInfo.loadGroupInfoItemList(assetInfo.currGroupId);
            } else {
                AlertError("操作失败，失败原因:" + result.errmsg);
            }
        })
    };

    //分组清查
    assetInfo.surveyAssetInventoryGroup = function (groupId) {
        var frame = layer.open({
            type: 2,
            title: '',
            shadeClose: true,
            shade: true,
            maxmin: true, //开启最大化最小化按钮
            area: ['90%', '90%'],
            content: '${pageContext.request.contextPath}/surveyAssetInfoGroup/inventory/' + groupId,
            cancel: function (index, layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
            },
            btnAlign: 'c',
            btn: ['保存', '关闭'],
            yes: function (index, layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
                iframe.newGetFormData(function (data) {
                    $.post('${pageContext.request.contextPath}/surveyAssetInfoGroup/saveGroupInventoryData', {
                        formData: JSON.stringify(data),
                        groupId: groupId
                    }, function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "操作成功!");
                            layer.closeAll('iframe');
                            assetInfo.loadSurveyAssetInfoGroupList();
                        } else {
                            AlertError("操作失败，失败原因:" + result.errmsg);
                        }
                    })
                });
            },
            btn2: function (index, layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
            }
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
                    });
                    childControl.empty().append(html);
                }
            }
        })
    }

    //显示损坏调查弹窗
    assetInfo.showEntityDamageModal = function () {
        var target = assetInfo.handleJquery(assetInfo.InfoItemBaseTable);
        var rows = target.bootstrapTable('getSelections');
        if (rows == null || rows.length <= 0) {
            notifyWarning("提示", "请选择清查对象数据");
            return false;
        }
        $('#boxEntityDamage').modal();
    }

    //保存损坏调查
    assetInfo.saveEntityDamage = function () {
        var target = assetInfo.handleJquery(assetInfo.InfoItemBaseTable);
        var rows = target.bootstrapTable('getSelections');
        if (rows == null || rows.length <= 0) {
            notifyWarning("提示", "请选择清查对象数据");
            return false;
        }
        var infoItemArray = [];
        $.each(rows, function (i, item) {
            infoItemArray.push(item.id);
        })

        var surveyAssetInventory = {};
        surveyAssetInventory.rimIsNormal = $("#rimIsNormal").val();
        surveyAssetInventory.entityIsDamage = $("#entityIsDamage").val();
        surveyAssetInventory.zoneDamage = [];
        surveyAssetInventory.entityDamage = [];
        surveyAssetInventory.otherProject = [];
        $("#damageSurvey").find('.form-group').each(function () {
            var zoneBit = {};
            var zoneProjectName = $(this).find('[name^=zoneProjectName]').val();
            var zoneProjectItem = $(this).find('[name^=zoneProjectItem]').val();
            if (zoneProjectName && zoneProjectItem) {
                zoneBit.zoneProjectName = zoneProjectName;
                zoneBit.zoneProjectItem = zoneProjectItem;
                surveyAssetInventory.zoneDamage.push(zoneBit);
            }

            var entity = {};
            var entityProjectName = $(this).find('[name^=entityProjectName]').val();
            var entityProjectItem = $(this).find('[name^=entityProjectItem]').val();
            if (entityProjectName && entityProjectItem) {
                entity.entityProjectName = entityProjectName;
                entity.entityProjectItem = entityProjectItem;
                surveyAssetInventory.entityDamage.push(entity);
            }

            var otherProject = {};
            var otherProjectName = $(this).find('[name^=otherProjectName]').val();
            var otherProjectItem = $(this).find('[name^=otherProjectItem]').val();
            if (otherProjectName && otherProjectItem) {
                otherProject.otherProjectName = otherProjectName;
                otherProject.otherProjectItem = otherProjectItem;
                surveyAssetInventory.otherProject.push(otherProject);
            }
        });

        $.post('${pageContext.request.contextPath}/surveyAssetInfoItem/damageSurveyBatch', {
            assetInfoItemIds: infoItemArray.join(),
            formData: JSON.stringify(surveyAssetInventory)
        }, function (result) {
            if (result.ret) {
                AlertSuccess('提示', '操作成功');
                $('#boxEntityDamage').modal('hide');
                assetInfo.loadSurveyAssetInfoItemBaseList();
            } else {
                AlertError("错误", result.errmsg);
            }
        }, 'json')
    }

    //批量一致性清查
    assetInfo.checkUniformityBatch = function () {
        var target = assetInfo.handleJquery(assetInfo.InfoItemBaseTable);
        var rows = target.bootstrapTable('getSelections');
        if (rows == null || rows.length <= 0) {
            notifyWarning("提示", "请选择清查对象数据");
            return false;
        }
        var infoItemArray = [];
        $.each(rows, function (i, item) {
            infoItemArray.push(item.id);
        })
        $.post('${pageContext.request.contextPath}/surveyAssetInfoItem/checkUniformityBatch', {
            assetInfoItemIds: infoItemArray.join()
        }, function (result) {
            if (result.ret) {
                AlertSuccess('提示', '操作成功');
                $('#boxEntityDamage').modal('hide');
                assetInfo.loadSurveyAssetInfoItemBaseList();
            } else {
                AlertError("错误", result.errmsg);
            }
        }, 'json')
    }

    //损坏调查显示隐藏
    assetInfo.triggeEntityDamage = function () {
        if ($("#rimIsNormal").val() == "不正常") {
            $(".showZoneAdd").show();
        } else {
            $(".zoneBit").empty();
            $(".showZoneAdd").hide();
        }
        if ($("#entityIsDamage").val() == "损坏") {
            $(".showEntityAdd").show();
        } else {
            $(".entity").empty();
            $(".showEntityAdd").hide();
        }
        if ($("#paymentStatus").val() == "不正常") {
            $(".showPaymentAdd").show();
            $("#showUploadFile").show();
        } else {
            $(".paymentItem").empty();
            $(".showPaymentAdd").hide();
            $("#showUploadFile").hide();
        }
    }

    //appendHtml
    assetInfo.entityDamageAppendHtml = function (className, fieldName) {
        var html = "<div class='row form-group' >";
        html += " <div class='col-md-12'>";
        html += "<div class='form-inline x-valid'>";

        html += "<label class='col-sm-1 control-label'>" + "项目" + "</label>";
        html += "<div class='col-sm-3'>";
        html += "<input type='text' required class='form-control input-full' name='" + fieldName + "Name'>";
        html += "</div>";

        html += "<label class='col-sm-1 control-label'>" + "明细" + "</label>";
        html += "<div class='col-sm-3'>";
        html += "<input type='text' required class='form-control input-full' name='" + fieldName + "Item'>";
        html += "</div>";

        html += " <div class='col-sm-1'>";
        html += "<input class='btn btn-warning btn-sm' type='button' value='X' onclick='$(this).closest(\".form-group\").remove();'>" + "</span>";
        html += "</div>";

        html += "</div>";
        html += "</div>";
        html += "</div>";

        $("." + className).append(html);
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

