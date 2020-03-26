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
                <h4 class="modal-title">资产清查组 添加</h4>
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
                                                    <select name="bisInventory" class="form-control input-full">
                                                        <option value="">请选择</option>
                                                        <option value="true">已完成清查</option>
                                                        <option value="false">未清查</option>
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
                                                    权证号
                                                </label>
                                                <div class="col-sm-1">
                                                    <input type="text" data-rule-maxlength="50" placeholder="权证号"
                                                           name="name"
                                                           class="form-control input-full">
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    状态
                                                </label>
                                                <div class="col-sm-1">
                                                    <select name="status" class="form-control input-full">
                                                        <option value="">请选择</option>
                                                        <option value="runing">进行中</option>
                                                        <option value="finish">已完成</option>
                                                    </select>
                                                </div>
                                                <div class="col-sm-3">
                                                    <button type="button" class="btn btn-info btn-sm"
                                                            onclick="assetInfo.loadSurveyAssetInfoItemBaseList(this);">
                                                        <span class="btn-label"><i class="fa fa-search"></i></span>
                                                        查询
                                                    </button>
                                                    <div class="dropdown" style="display: inline;margin-left: 5px;">
                                                        <button type="button"
                                                                class="btn btn-primary dropdown-toggle btn-sm"
                                                                data-toggle="dropdown"
                                                                aria-expanded="false">
                                                            认领的权证添加到组中
                                                        </button>
                                                        <div class="dropdown-menu" role="menu"
                                                             data-name="surveyAssetInfoGroup">

                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-sm-2 ">
                                                    <button type="button" class="btn btn-info btn-sm"
                                                            onclick="assetInfo.taskCopy(this) ;"><i
                                                            class="fa fa-copy" aria-hidden="true"></i> 拷贝
                                                    </button>
                                                    <button type="button" class="btn btn-warning btn-sm"
                                                            onclick="assetInfo.taskPaste(this) ;"><i
                                                            class="fa fa-clipboard" aria-hidden="true"></i>粘贴
                                                    </button>
                                                </div>
                                                <div class="col-sm-2 ">
                                                    <div class="input-group ">
                                                        <input type="hidden" name="copyId">
                                                        <input type="text" readonly="readonly" name="copyName"
                                                               class="form-control form-control-sm"
                                                               placeholder="暂无复制数据">
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
                                                        <option value="runing">进行中</option>
                                                        <option value="finish">已完成</option>
                                                    </select>
                                                </div>
                                                <div class="col-sm-3">
                                                    <button type="button" class="btn btn-info btn-sm"
                                                            onclick="assetInfo.loadSurveyAssetInfoGroupList(this);">
                                                        <span class="btn-label"><i class="fa fa-search"></i></span>
                                                        查询
                                                    </button>
                                                    <button style="margin-left: 5px" class="btn btn-success btn-sm"
                                                            type="button" data-toggle="modal"
                                                            onclick="assetInfo.addSurveyAssetInfoGroup();">
                                                        <span class="btn-label"><i class="fa fa-plus"></i></span>资产清查组
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
        assetInfo.ajaxServerFun({}, "/surveyAssetInventory/parseSurveyAssetInventory/${surveyAssetInfo.id}/" + inventoryId + "/" + masterId + "/" + type, "post", callback)
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
                var str = value;
                if (row.bisInventory) {
                    str += "<button type='button' class='btn btn-xs btn-warning' disabled='disabled' style='margin-left: 5px;'>已经被清查</button>";
                }
                return str;
            }
        });
        cols.push({field: 'seat', title: '坐落', width: "20%"});
        cols.push({field: 'certUse', title: '证载用途', width: "5%"});
        cols.push({field: 'practicalUse', title: '实际用途', width: "5%"});
        cols.push({field: 'floorArea', title: '面积', width: "5%"});
        cols.push({
            field: 'bisInventory', title: '状态', width: "10%", formatter: function (value, row, index) {
                if (value) {
                    return "已完成清查";
                } else {
                    return "未清查";
                }
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


//                str += "<button type='button' style='margin-left: 5px;'   onclick='assetInfo.taskPaste(this," + row.id + ");' data-type='group' title='粘贴' class='btn btn-xs btn-warning ' ><i class='fa fa-paste fa-white'></i> <span>粘贴</span></button>";
//                str += "<button type='button' style='margin-left: 5px;'   onclick='assetInfo.taskCopy(this," + row.id + ");' data-type='group' title='复制' class='btn btn-xs btn-warning btn-copy' ><i class='fa fa-copy fa-white'></i> <span>复制</span></button>";

                return str;
            }
        });
        cols.push({
            field: 'status', title: '状态', width: "10%", formatter: function (value, row, index) {
                var str = "";
                if (value) {
                    if (value == 'runing') {
                        str = "进行中";
                    }
                    if (value == 'finish') {
                        str = "已完成";
                    }
                }
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
            }
        });
    };

    assetInfo.selectClaimData = function () {
        var target = assetInfo.handleJquery(assetInfo.declareRecordTable);
        var rows = target.bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            notifyWarning("勾选的数据为0", "请勾选需要认领的权证!");
        } else {
            var idArray = [];
            var num = 0;
            rows.forEach(function (ele, index) {
                if (!ele.bisInventory) {
                    idArray.push(ele.id);
                } else {
                    num++;
                }
            });
            if (num != 0) {
                notifyWarning("警告", "此次选择的权证存在已经被清查的数据,请检查!");
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
                    assetInfo.addSurveyAssetInfoItem(data, function () {
                        AlertSuccess("添加成功", "已经添加到 认领列表中");
                        target.bootstrapTable('uncheckAll');
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
        var frm = $(_this).closest("form") ;
        var target = assetInfo.handleJquery(assetInfo.InfoItemBaseTable);
        var rows = target.bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            notifyWarning("提示", "请勾选认领的权证中的一个!");
        } else {
            if (rows.length > 1){
                notifyWarning("提示", "只能拷贝一个!");
                target.bootstrapTable('uncheckAll');
                return false ;
            }
            var data = rows[0] ;
            if (data.groupId){
                notifyWarning("提示", "此数据属于清查组不能拷贝!");
                target.bootstrapTable('uncheckAll');
                return false ;
            }
            if (! data.inventoryId){
                notifyWarning("提示", "此数据没有完成清查业务 不能拷贝!");
                target.bootstrapTable('uncheckAll');
                return false ;
            }
            frm.find("[name=copyId]").val(data.id) ;
            frm.find("[name=copyName]").val(data.name) ;
            target.bootstrapTable('uncheckAll');
            notifySuccess("成功", "拷贝成功!");
        }
    };

    /***
     * 粘贴清查具体数据
     */
    assetInfo.taskPaste = function (_this) {
        var frm = $(_this).closest("form") ;
        var target = assetInfo.handleJquery(assetInfo.InfoItemBaseTable);
        var rows = target.bootstrapTable('getSelections');
        var id = frm.find("[name=copyId]").val();
        if (!id) {
            notifyInfo('提示', '没有复制对象');
            return false;
        }
        if (!rows || rows.length <= 0) {
            notifyWarning("提示", "请勾选认领的权证中的一个!");
        } else {
            var data = target.bootstrapTable('getRowByUniqueId', id);
            AlertConfirm("粘贴操作", "是否确认", function (flag) {
                assetInfo.parseSurveyAssetInventory(data.inventoryId, "unit", id, function () {
                    notifySuccess("成功", "粘贴完成");
                    assetInfo.loadSurveyAssetInfoItemBaseList();
                });
            });
        }

    };

    /*
     资产清查组  清查数据
     */
    assetInfo.groupHandel = function (id) {
        var obj = assetInfo.handleJquery(assetInfo.groupTable).bootstrapTable('getRowByUniqueId', id);
        var inventoryId = 0;
        if (obj.inventoryId) {
            inventoryId = obj.inventoryId;
        }
        assetInfo.getSurveyAssetInfoItemListByQuery({groupId: id}, function (data) {
            if (data.length >= 1) {
                var item = data[0];
                assetInfo.surveyAssetInventoryHandle(inventoryId, item.declareId, "group", id);
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
        assetInfo.surveyAssetInventoryHandle(inventoryId, item.declareId, "unit", item.id);
    };

    /**
     * 资产清查 进入 具体的业务页面
     * @param inventoryId
     * @param declareId
     * @param type
     * @param masterId
     */
    assetInfo.surveyAssetInventoryHandle = function (inventoryId, declareId, type, masterId) {
        var frame = layer.open({
            type: 2,
            title: '资产清查',
            shadeClose: true,
            shade: true,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: '${pageContext.request.contextPath}/surveyAssetInventory/view/${projectPlanDetails.projectId}/${projectPlanDetails.id}/' + inventoryId + "/" + declareId,
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
                        }
                        //单个的方式清查
                        if (type == 'unit') {
                            if (masterId) {
                                assetInfo.getSurveyAssetInfoItemById(masterId, function (obj) {
                                    obj.inventoryId = result.surveyAssetInventory.id;
                                    obj.status = 'finish';
                                    assetInfo.saveAndUpdateSurveyAssetInfoItem(obj, function () {
                                        assetInfo.loadSurveyAssetInfoItemBaseList();
                                    });
                                })
                            }
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
     *
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
            notifyWarning("请勾选上面的权证列表", "勾选的将添加到所选择的清查组中!");
        } else {
            var data = [];
            var num = 0;
            rows.forEach(function (ele, index) {
                ele.groupId = groupData.id;
                ele.groupName = groupData.groupName;
                data.push(ele);
                if (ele.status == 'finish') {
                    num++;
                }
            });
            if (num != 0) {
                notifyWarning("警告", "添加到清查组的认领权证有已经完成清查任务的认领权证,请检查!");
                target.bootstrapTable('uncheckAll');
                return false;
            }
            if (data.length >= 1) {
                AlertConfirm("是否确认添加当前选中数据到清查组中", "确定", function (flag) {
                    assetInfo.addSurveyAssetInfoItem(data, function () {
                        AlertSuccess("添加成功", "已经添加到 《" + groupData.groupName + "》 中");
                        target.bootstrapTable('uncheckAll');
                        assetInfo.loadSurveyAssetInfoItemBaseList();
                    });
                });
            } else {
                target.bootstrapTable('uncheckAll');
                notifyWarning("警告", "此次选择的权证都已经被清查了!");
            }
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
        assetInfo.loadSurveyAssetInfoItemList({groupId: id}, assetInfo.handleJquery(assetInfo.InfoItemTable));
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
        assetInfo.deleteSurveyAssetInfoGroupById(id, function () {
            assetInfo.loadSurveyAssetInfoGroupList();
        })
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

    assetInfo.loadSurveyAssetInfoItemList = function (query, table) {
        var cols = [];
        var selector = table.selector;
        cols.push({field: 'name', title: '名称', width: "40%"});
        cols.push({
            field: 'id', title: '操作', width: "10%", formatter: function (value, row, index) {
                var str = "";
                str += '<button type="button" onclick="assetInfo.removeGroupSurveyAssetInfoItem(' + row.id + ",'" + selector + "'" + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn   btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="从此组中移除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';
                return str;
            }
        });
        table.bootstrapTable('destroy');
        TableInit(table, "${pageContext.request.contextPath}/surveyAssetInfoItem/getBootstrapTableVo", cols, query, {
            method: "get",
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        });
    };

    /**
     * 删除组下面的从表
     * @param id
     * @param selector
     */
    assetInfo.delSurveyAssetInfoItem = function (id, selector) {
        assetInfo.deleteSurveyAssetInfoItemById(id, function () {
            $(selector).bootstrapTable('refresh');
        });
    };

    /**
     * 移除组中添加的数据
     * @param id
     * @param selector
     */
    assetInfo.removeGroupSurveyAssetInfoItem = function (id, selector) {
        var data = $(selector).bootstrapTable('getRowByUniqueId', id);
        data.groupId = 0;
        data.groupName = '';
        assetInfo.saveAndUpdateSurveyAssetInfoItem(data, function () {
            notifySuccess("成功", "移除成功!");
            $(selector).bootstrapTable('refresh');
            assetInfo.handleJquery(assetInfo.InfoItemBaseTable).bootstrapTable('refresh');
        }, true);
    };

    assetInfo.delSurveyAssetInfoItemByDeclareId = function (id, selector) {
        var data = $(selector).bootstrapTable('getRowByUniqueId', id);
        if (data.groupId) {
            AlertError("此条数据已经加入组中", "请进入组中移除,然后在进入认领列表中删除");
        } else {
            assetInfo.delSurveyAssetInfoItem(id, selector);
        }
    };

    assetInfo.loadSurveyAssetInfoItemBaseList = function (_this) {
        var options = {
            projectId: '${projectInfo.id}',
            assetInfoId:${surveyAssetInfo.id},
            creator: '${projectPlanDetails.executeUserAccount}'
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
        cols.push({field: 'name', title: '权证号', width: "33%"});
        cols.push({
            field: 'id', title: '操作', width: "40%", formatter: function (value, row, index) {
                var str = "";
                str += '<button type="button" onclick="assetInfo.delSurveyAssetInfoItemByDeclareId(' + row.id + ",'" + selector + "'" + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn   btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                str += '<i class="fa fa-minus"></i>';
                str += '</button>';

                str += '<button type="button" onclick="assetInfo.itemHandel(' + value + ')" style="margin-left: 5px;" class="btn   btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="清查操作">';
                str += '<i class="fa fa-cog"></i>';
                str += '</button>';

                return str;
            }
        });
        cols.push({
            field: 'status', title: '状态/属于组', width: "10%", formatter: function (value, row, index) {
                var str = "";
                if (row.groupId) {
                    str = row.groupName + "";
                } else {
                    if (value) {
                        if (value == 'runing') {
                            str = "进行中";
                        }
                        if (value == 'finish') {
                            str = "已完成";
                        }
                    }
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


    $(document).ready(function () {
        assetInfo.loadDeclareRecordList();
        assetInfo.loadSurveyAssetInfoGroupList();
        assetInfo.loadSurveyAssetInfoItemBaseList();
    });

</script>


<script type="text/javascript">

    function submit(mustUseBox) {
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
    }


</script>


</html>

