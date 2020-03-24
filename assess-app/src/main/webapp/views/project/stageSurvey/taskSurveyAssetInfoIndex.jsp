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
                                                名称<span class="symbol required"></span>
                                            </label>
                                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                                <input type="text" class="form-control input-full" name="groupName"
                                                       placeholder="名称" required="required">
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
                                                <div class="col-sm-3">
                                                    <button type="button" class="btn btn-info btn-sm"
                                                            onclick="assetInfo.loadDeclareRecordList(this);">
                                                        <i class="fa fa-search"></i>
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
                                            </div>
                                        </div>
                                    </div>
                                    <div id="grouptoolbar">
                                        <button style="margin-left: 5px" class="btn btn-success btn-sm"
                                                type="button" data-toggle="modal"
                                                onclick="assetInfo.showSurveyAssetInfoGroup();">
                                            <span class="btn-label"><i class="fa fa-plus"></i></span>资产清查组
                                        </button>
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
     * 资产清查 save
     * @param data
     * @param callback
     */
    assetInfo.saveAndUpdateSurveyAssetInfoItem = function (data, callback) {
        assetInfo.ajaxServerFun({formData: JSON.stringify(data)}, "/surveyAssetInfoItem/saveAndUpdateSurveyAssetInfoItem", "post", callback);
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

    assetInfo.declareRecordTable = $("#tb_declare_record_list");
    assetInfo.groupTable = $("#tbSurveyAssetInfoGroupList");
    assetInfo.groupBox = $("#boxSurveyAssetInfoGroup");

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
        var cols = [];
        cols.push({checkbox: true, width: "5%"});
        cols.push({field: 'name', title: '权证号'});
        cols.push({field: 'seat', title: '坐落'});
        cols.push({field: 'certUse', title: '证载用途'});
        cols.push({field: 'practicalUse', title: '实际用途'});
        cols.push({field: 'floorArea', title: '面积'});
        cols.push({
            field: 'id', title: '清查操作', width: "10%", formatter: function (value, row, index) {
                var str = "";
                if (!row.bisInventory) {
                    str += '<button type="button" onclick="assetInfo.itemHandel(' + value + ')" style="margin-left: 5px;" class="btn   btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="清查操作">';
                    str += '<i class="fa fa-cog"></i>';
                    str += '</button>';
                } else {
                    str += "<label class='label label-warning'>已经被清查</label>";
                }
                return str;
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

    assetInfo.loadSurveyAssetInfoGroupList = function () {
        var target = assetInfo.handleJquery(assetInfo.groupTable);
        var cols = [];
        cols.push({field: 'groupName', title: '组名称', width: "40%"});
        cols.push({
            field: 'id', title: '清查操作', width: "10%", formatter: function (value, row, index) {
                var str = "";
                str += '<button type="button" onclick="assetInfo.groupHandel(' + row.id + ')" style="margin-left: 5px;" class="btn   btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="清查操作">';
                str += '<i class="fa fa-cog"></i>';
                str += '</button>';
                return str;
            }
        });
        target.bootstrapTable('destroy');
        TableInit(target, "${pageContext.request.contextPath}/surveyAssetInfoGroup/getBootstrapTableVo", cols, {assetInfoId: '${surveyAssetInfo.id}'}, {
            method: "get",
            showColumns: false,
            showRefresh: false,
            toolbar: "#grouptoolbar",
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        });
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
            var item = data[0];
            assetInfo.surveyAssetInventoryHandle(inventoryId, item.declareId, "group", id);
        });
    };

    /**
     * 单个权证   清查数据
     */
    assetInfo.itemHandel = function (id) {
        var inventoryId = 0;
        assetInfo.getSurveyAssetInfoItemListByQuery({
            groupId: 0,
            assetInfoId: '${surveyAssetInfo.id}',
            declareId: id
        }, function (data) {
            if (data.length == 0) {
                assetInfo.surveyAssetInventoryHandle(inventoryId, id, "unit", null);
            } else {
                var item = data[0];
                if (item.inventoryId) {
                    inventoryId = item.inventoryId;
                }
                assetInfo.surveyAssetInventoryHandle(inventoryId, id, "unit", item.id);
            }
        })
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
                                assetInfo.saveAndUpdateSurveyAssetInfoGroup(obj, function () {
                                    assetInfo.getSurveyAssetInfoItemListByQuery({groupId: masterId}, function (data) {
                                        var ids = [];
                                        $.each(data, function (i, item) {
                                            ids.push(item.declareId);
                                        });
                                        assetInfo.ajaxServerMethod({id: ids.join(",")}, "/declareRecord/getDeclareRecordListByIds", "get", function (data) {
                                            var arrData = [];
                                            $.each(data, function (i, item) {
                                                item.bisInventory = true;
                                                arrData.push(item);
                                            });
                                            assetInfo.ajaxServerMethod({fomData: JSON.stringify(arrData)}, "/declareRecord/saveAndUpdateDeclareRecord", "post", function () {
                                                assetInfo.loadDeclareRecordList();
                                            });
                                        });
                                    });
                                    assetInfo.loadSurveyAssetInfoGroupList();
                                });
                            });
                        }
                        //单个的方式清查
                        if (type == 'unit') {
                            var record = assetInfo.handleJquery(assetInfo.declareRecordTable).bootstrapTable('getRowByUniqueId', declareId);
                            if (masterId) {
                                assetInfo.getSurveyAssetInfoItemById(masterId, function (obj) {
                                    obj.inventoryId = result.surveyAssetInventory.id;
                                    assetInfo.saveAndUpdateSurveyAssetInfoItem(obj, function () {
                                        record.bisInventory = true;
                                        assetInfo.saveDeclareRecord(record, function () {
                                            assetInfo.loadDeclareRecordList();
                                        });
                                    });
                                })
                            } else {
                                var obj = {
                                    groupId: 0,
                                    declareId: declareId,
                                    inventoryId: result.surveyAssetInventory.id,
                                    assetInfoId: '${surveyAssetInfo.id}'
                                };
                                assetInfo.saveAndUpdateSurveyAssetInfoItem(obj, function () {
                                    record.bisInventory = true;
                                    assetInfo.saveDeclareRecord(record, function () {
                                        assetInfo.loadDeclareRecordList();
                                    });
                                });
                            }
                        }
                        layer.closeAll('iframe');
                        notifyInfo("成功", "清查成功");
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
    assetInfo.showSurveyAssetInfoGroup = function () {
        var target = assetInfo.handleJquery(assetInfo.declareRecordTable);
        var rows = target.bootstrapTable('getSelections');
        if (!rows || rows.length <= 0) {
            notifyWarning("警告", "请选择 需要添加到组的权证!");
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
                AlertConfirm("是否确认添加当前选中数据到清查组中", "确定", function (flag) {
                    var target = assetInfo.handleJquery(assetInfo.groupBox);
                    var frm = target.find("form");
                    frm.clearAll();
                    frm.initForm({declareIds: idArray.join(",")});
                    target.modal("show");
                });
            } else {
                target.bootstrapTable('uncheckAll');
                notifyWarning("警告", "此次选择的权证都已经被清查了!");
            }
        }
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
        var declareIds = data.declareIds;
        assetInfo.saveAndUpdateSurveyAssetInfoGroup(data, function (item) {
            target.modal("hide");
            var arr = declareIds.split(",");
            $.each(arr, function (i, declareId) {
                var obj = assetInfo.handleJquery(assetInfo.declareRecordTable).bootstrapTable('getRowByUniqueId', declareId);
                assetInfo.saveAndUpdateSurveyAssetInfoItem({
                    declareId: declareId,
                    groupId: item.id,
                    name:obj.name
                });
            });
            notifySuccess("成功", "添加资产清查组成功!");
            assetInfo.loadSurveyAssetInfoGroupList();
            assetInfo.loadDeclareRecordList();
        });
    };


    $(document).ready(function () {
        assetInfo.loadDeclareRecordList();
        assetInfo.loadSurveyAssetInfoGroupList();
    });

</script>


<script type="text/javascript">

    function submit(mustUseBox) {
        var formData = JSON.stringify({});
        if ("${processInsId}" != "0") {
            submitEditToServer(formData);
        } else {
            submitToServer(formData, mustUseBox);
        }
    }


</script>


</html>

