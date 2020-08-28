<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                <h4 class="modal-title">资产清查组 </h4>
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
                                                <label class="form-control input-full" name="groupName"></label>
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
                                <form class="form-horizontal">
                                    <div class="form-group form-inline">
                                        <label for="queryName" class="col-sm-1 col-form-label">权证号</label>
                                        <div class="col-sm-3 p-0">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="权证号" id="queryName" name="name"
                                                   class="form-control input-full">
                                        </div>
                                        <div class="col-sm-1">
                                            <button class="btn btn-info  btn-sm" type="button"
                                                    onclick="assetInfo.loadSurveyAssetInfoItemBaseList(this);">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                                查询
                                            </button>
                                        </div>
                                    </div>
                                </form>
                                <table class="table table-bordered" id="tb_infoBaseItem_list"></table>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12" style="display: none;">
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
                                    <div id="baseInfoGroupToolbar">
                                        <div class="input-group">
                                            <input type="text" class="form-control" placeholder="组名称" name="groupName">
                                            <div class="input-group-prepend">
                                                <button class="btn btn-info btn-sm" type="button"
                                                        onclick="assetInfo.loadSurveyAssetInfoGroupList(this);">
                                                    <i class="fa fa-search"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <table class="table table-bordered" id="tbSurveyAssetInfoGroupList"></table>
                                </form>

                            </div>
                        </div>
                    </div>

                    <div class="col-md-12">
                        <div class="x_panel card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="x_title card-title">
                                        清查统计
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
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <div class="form-inline">
                                                <label class="col-sm-1 control-label">
                                                    清查所有数量
                                                </label>
                                                <div class="col-sm-2">
                                                    <label class="form-control input-full">${surveyAssetInfo.count}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    已经完成清查
                                                </label>
                                                <div class="col-sm-2">
                                                    <label class="form-control input-full">${surveyAssetInfo.finishCount}</label>
                                                </div>
                                                <label class="col-sm-1 control-label">
                                                    本次流程清查的数量
                                                </label>
                                                <div class="col-sm-2">
                                                    <label class="form-control input-full">${surveyAssetInfo.thisCount}</label>
                                                </div>
                                            </div>
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

    var assetInfo = {};


    assetInfo.groupTable = $("#tbSurveyAssetInfoGroupList");
    assetInfo.groupBox = $("#boxSurveyAssetInfoGroup");
    assetInfo.InfoItemTable = $("#tb_infoItem_list");
    assetInfo.InfoItemBaseTable = $("#tb_infoBaseItem_list");

    assetInfo.handleJquery = function (obj) {
        if (obj instanceof jQuery) {
            return obj;
        } else {
            return $(obj.selector);
        }
    };


    /*
     资产清查组  清查数据
     */
    assetInfo.groupHandel = function (id) {
        var obj = assetInfo.handleJquery(assetInfo.groupTable).bootstrapTable('getRowByUniqueId', id);
        var inventoryId = 0;
        if (obj.inventoryId) {
            assetInfo.surveyAssetInventoryHandle(obj.inventoryId, 0, obj.groupName);
        } else {
            notifyInfo('提示', '无相关业务数据');
        }
    };

    /**
     * 认领权证   清查数据
     */
    assetInfo.itemHandel = function (id) {
        var obj = assetInfo.handleJquery(assetInfo.InfoItemBaseTable).bootstrapTable('getRowByUniqueId', id);
        if (obj.inventoryId) {
            assetInfo.surveyAssetInventoryHandle(obj.inventoryId, obj.declareId, obj.name);
        } else {
            notifyInfo('提示', '无相关业务数据');
        }
    };

    /**
     * 资产清查 进入 具体的业务页面
     * @param inventoryId
     * @param declareId
     */
    assetInfo.surveyAssetInventoryHandle = function (inventoryId, declareId, masterName) {
        var frame = layer.open({
            type: 2,
            title: '',
            shadeClose: true,
            shade: true,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: '${pageContext.request.contextPath}/surveyAssetInventory/detailView/${projectPlanDetails.projectId}/${projectPlanDetails.id}/' + inventoryId + "/" + declareId + "/" + masterName,
            cancel: function (index, layero) {
                var iframe = window[layero.find('iframe')[0]['name']];
                //放弃按钮 不需要做处理
            },
            btnAlign: 'c',
            btn: ['关闭'],
            yes: function (index, layero) {
                layer.closeAll('iframe');
            },
            btn2: function (index, layero) {
                layer.closeAll('iframe');
            }
        });
        layer.full(frame);
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
        var cols = [];
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
            field: 'id', title: '操作', width: "10%", formatter: function (value, row, index) {
                var str = "";
                if (row.groupId) {

                } else {
                    str += '<button type="button" onclick="assetInfo.itemHandel(' + value + ')" style="margin-left: 5px;" class="btn   btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看清查业务数据">';
                    str += '<i class="fa fa-search"></i>';
                    str += '</button>';
                }
                return str;
            }
        });

        target.bootstrapTable('destroy');
        TableInit(target, "${pageContext.request.contextPath}/surveyAssetInfoItem/getBootstrapTableVo", cols, data, {
            method: "get",
            toolbar: "#baseInfoItemToolbar",
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
        var options = {
            projectId: '${projectInfo.id}',
            groupId: 0,
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
        var target = assetInfo.handleJquery(assetInfo.groupTable);
        var cols = [];
        cols.push({field: 'groupName', title: '组名称', width: "30%"});
        cols.push({
            field: 'id', title: '操作', width: "30%", formatter: function (value, row, index) {
                var str = "";

                str += '<button type="button" onclick="assetInfo.groupHandel(' + row.id + ')" style="margin-left: 5px;" class="btn   btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看清查业务数据">';
                str += '<i class="fa fa-search"></i>';
                str += '</button>';


                str += '<button type="button" onclick="assetInfo.findSurveyAssetInfoGroupDetail(' + row.id + ')" style="margin-left: 5px;" class="btn   btn-info  btn-xs tooltips"  data-placement="bottom" data-original-title="查看包含的权证列表">';
                str += '包含的权证列表 <i class="fa fa-search"></i>';
                str += '</button>';


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
        target.bootstrapTable('destroy');
        TableInit(target, "${pageContext.request.contextPath}/surveyAssetInfoGroup/getBootstrapTableVo", cols, data, {
            method: "get",
            showColumns: false,
            showRefresh: false,
            search: false,
            toolbar: "#baseInfoGroupToolbar",
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
                var item = target.bootstrapTable('getData');
                if (item.length >= 1) {
                    target.closest(".card").parent().show();
                }
            }
        });
    };

    assetInfo.findSurveyAssetInfoGroupDetail = function (id) {
        var target = assetInfo.handleJquery(assetInfo.groupBox);
        var frm = target.find("form");
        frm.clearAll();
        var data = assetInfo.handleJquery(assetInfo.groupTable).bootstrapTable('getRowByUniqueId', id);
        frm.initForm(data);
        assetInfo.loadSurveyAssetInfoItemList({groupId: id}, assetInfo.handleJquery(assetInfo.InfoItemTable));
        target.modal("show");
    };

    assetInfo.loadSurveyAssetInfoItemList = function (query, table) {
        var cols = [];
        cols.push({field: 'name', title: '名称', width: "40%"});
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


    $(document).ready(function () {
        assetInfo.loadSurveyAssetInfoItemBaseList();
        assetInfo.loadSurveyAssetInfoGroupList();
    });

</script>


<script type="text/javascript">
    function saveform() {
        saveApprovalform("");
    }
</script>
</html>
