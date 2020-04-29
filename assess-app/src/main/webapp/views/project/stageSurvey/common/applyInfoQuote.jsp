<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--引用项目中的楼盘--%>
<div id="divBoxProjectData" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">项目</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmQuery" class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group ">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                项目名称
                                            </label>
                                            <div class="col-sm-2">
                                                <input type="text" data-rule-maxlength="50"
                                                       placeholder="项目名称" id="queryName" name="queryName"
                                                       class="form-control input-full">
                                            </div>
                                            <button type="button" class="btn btn-primary"
                                                    onclick="projectData.prototype.loadProjectDataList()">
                                                查询
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <table class="table table-bordered" id="projectDataList">
                                        </table>
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
<div id="divBoxProjectItemData" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">查勘及案例</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <table class="table table-bordered" id="projectCaseItemList">
                                        </table>
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
<div id="divBoxProjectEstate" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">楼盘</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="row form-group ">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                名称
                            </label>
                            <div class="col-sm-3">
                                <input type="text" data-rule-maxlength="50"
                                       placeholder="名称" id="queryEstateName" name="queryEstateName"
                                       class="form-control input-full">
                            </div>
                            <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                    onclick="projectData.prototype.loadDataList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                查询
                            </button>
                        </div>
                    </div>
                </div>
                <div class="row form-group ">
                    <div class="col-md-12">
                        <table class="table table-bordered" id="projectEstateTable">
                        </table>
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
<div id="divBoxProjectBuild" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">楼栋</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="row form-group ">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                名称
                            </label>
                            <div class="col-sm-3">
                                <input type="text" data-rule-maxlength="50"
                                       placeholder="名称" id="queryBuildName" name="queryBuildName"
                                       class="form-control input-full">
                            </div>
                            <button style="margin-left: 10px" class="btn btn-info  btn-sm"
                                    type="button"
                                    onclick="projectBuild.prototype.loadDataList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                查询
                            </button>
                        </div>
                    </div>
                </div>
                <div class="row form-group ">
                    <div class="col-md-12">
                        <table class="table table-bordered" id="projectBuildTable">
                        </table>
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
<div id="divBoxProjectUnit" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">单元</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="row form-group ">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                名称
                            </label>
                            <div class="col-sm-3">
                                <input type="text" data-rule-maxlength="50"
                                       placeholder="名称" id="queryUnitName" name="queryUnitName"
                                       class="form-control input-full">
                            </div>
                            <button style="margin-left: 10px" class="btn btn-info  btn-sm"
                                    type="button"
                                    onclick="projectUnit.prototype.loadDataList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                查询
                            </button>
                        </div>
                    </div>
                </div>
                <div class="row form-group ">
                    <div class="col-md-12">
                        <table class="table table-bordered" id="projectUnitTable">
                        </table>
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
<div id="divBoxProjectHouse" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">房屋</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="row form-group ">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                名称
                            </label>
                            <div class="col-sm-3">
                                <input type="text" data-rule-maxlength="50"
                                       placeholder="名称" id="queryHouseName" name="queryHouseName"
                                       class="form-control input-full">
                            </div>
                            <button style="margin-left: 10px" class="btn btn-info  btn-sm"
                                    type="button"
                                    onclick="projectHouse.prototype.loadDataList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                查询
                            </button>
                        </div>
                    </div>
                </div>
                <div class="row form-group ">
                    <div class="col-md-12">
                        <table class="table table-bordered" id="projectHouseTable">
                        </table>
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

<script type="application/javascript">
    //填充对应的数据
    function autocompleteData(data) {
        if ("estate" == "${tbType}") {
            estateCommon.initById(data.id);
        }

        if ("building" == "${tbType}") {
            buildingCommon.showBuildingView(data);
        }

        if ("unit" == "${tbType}") {
            unitCommon.showUnitView(data);
        }

        if ("house" == "${tbType}") {
            houseCommon.showHouseView(data);
        }
    }
</script>
<script type="application/javascript">
    function infoDetail(id, tbType) {
        $.ajax({
            url: "${pageContext.request.contextPath}/basicAlternativeCase/infoDetail",
            type: "post",
            dataType: "json",
            data: {
                id: id
            },
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        var url = '${pageContext.request.contextPath}/basicApplyBatch/informationDetail?';
                        url += 'applyBatchId=' + result.data.applyBatchId;
                        url += '&formClassify=' + result.data.formClassify;
                        url += '&formType=' + result.data.formType;
                        url += '&tableId=' + result.data.tableId;
                        url += '&tableName=' + result.data.tableName;
                        url += '&tbType=' + tbType;
                        url += '&planDetailsId=' + result.data.planDetailsId;
                        openWin(url, function () {
                        })
                    }

                }
                else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }

    // 查找项目及楼盘
    var projectData = function () {

    };
    projectData.prototype = {
        config: function () {
            var data = {};
            data.table = "projectDataList";
            data.frm = "frmQuery";
            data.box = "divBoxProjectData";
            data.itemBox = "divBoxProjectItemData";
            data.itemTable = "projectCaseItemList";
            data.houseBox = "divBoxProjectEstate";
            data.houseTable = "projectEstateTable";
            return data;
        },
        showProjectDataModel: function () {
            $("#" + projectData.prototype.config().frm).clearAll();
            projectData.prototype.loadProjectDataList();
            $('#' + projectData.prototype.config().box).modal("show");
        },
        loadProjectDataList: function () {
            var cols = [];
            cols.push({field: 'projectName', title: '项目名称'});
            cols.push({
                field: 'projectClassName', title: '项目类型', formatter: function (value, row, index) {
                    var s = "";
                    if (row.projectClassName) {
                        s += row.projectClassName;
                    }
                    if (row.projectClassName) {
                        s += "/" + row.projectTypeName;
                    }
                    if (row.projectClassName) {
                        s += "/" + row.projectCategoryName;
                    }
                    return s;
                }
            });
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="查勘及案例" onclick="projectData.prototype.checkTaskData(' + index + ')">查勘及案例</button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + projectData.prototype.config().table).bootstrapTable('destroy');
            TableInit(projectData.prototype.config().table, "${pageContext.request.contextPath}/projectCenter/getProjectList", cols, {
                projectName: $("#queryName").val(),
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $(".tooltips").tooltip();
                }
            });
        },
        checkTaskData: function (index) {
            var row = $("#projectDataList").bootstrapTable('getData')[index];
            projectData.prototype.loadProjectTaskItemList(row.id, row.projectCategoryId);
            $('#' + projectData.prototype.config().itemBox).modal("show");
        },
        loadProjectTaskItemList: function (projectId, projectCategoryId) {
            var cols = [];
            cols.push({field: 'projectPhaseName', title: '名称'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str += '<button type="button" class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="查看信息" onclick="projectData.prototype.showHouseModel(' + row.id + ')"><i class="fa fa-search fa-white"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + projectData.prototype.config().itemTable).bootstrapTable('destroy');
            TableInit(projectData.prototype.config().itemTable, "${pageContext.request.contextPath}/basicApply/getProjectCaseItemList", cols, {
                projectId: projectId,
                projectCategoryId: projectCategoryId,
                basicApplyTypeId: "${empty basicApplyTypeId?type:basicApplyTypeId}"
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $(".tooltips").tooltip();
                }
            });
        },
        showModel: function () {
            projectData.prototype.loadDataList();
            $('#' + projectData.prototype.config().houseBox).modal("show");
        },

        loadDataList: function () {
            var cols = [];
            cols.push({field: 'name', title: '名称'});
            cols.push({
                field: 'id', title: '查询', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<button type="button" style="margin-left: 10px;" class="btn btn-xs btn-info tooltips"  data-placement="top" data-original-title="查看" onclick="projectData.prototype.findData(' + row.id + ',\'estate\')"><i class="fa fa-search fa-white"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="引用" onclick="projectData.prototype.quote(' + row.id + ')"><i class="fa fa-check"></i></button>';
                    str += '<button type="button" onclick="projectData.prototype.removeData(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                    str += '<i class="fa fa-minus"></i>';
                    str += '</button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + projectData.prototype.config().houseTable).bootstrapTable('destroy');
            TableInit(projectData.prototype.config().houseTable, "${pageContext.request.contextPath}/basicAlternativeCase/getBasicAlternativeCaseList", cols, {
                name: $("#queryEstateName").val(),
                tbType: "estate",
                planDetailsId:'${planDetailsId}'
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
                    url: "${pageContext.request.contextPath}/basicAlternativeCase/deleteDataById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除数据成功");
                            projectData.prototype.loadDataList();
                        }
                        else {
                            AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                })
            })
        },
        findData: function (id, buildType, estateId) {
            infoDetail(id, buildType);
        },
        quote: function (id) {
            $.ajax({
                url: '${pageContext.request.contextPath}/basicEstate/quoteFromAlternative',
                data: {
                    id: id,
                    tableId:'${tbId}'
                },
                type: "get",
                success: function (result) {
                    if (result.ret) {
                        if (result.data != null) {
                            autocompleteData(result.data);
                            $('#' + projectData.prototype.config().houseBox).modal("hide");
                            $('#' + projectData.prototype.config().itemBox).modal("hide");
                            $('#' + projectData.prototype.config().box).modal("hide");
                        }
                    } else {
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                }
            })
        }

    };

    //楼栋
    var projectBuild = function () {
    };
    projectBuild.prototype = {
        config: function () {
            var data = {};
            data.table = "projectBuildTable";
            data.box = "divBoxProjectBuild";
            return data;
        },
        showModel: function () {
            projectBuild.prototype.loadDataList();
            $('#' + projectBuild.prototype.config().box).modal("show");
        },
        loadDataList: function () {
            var cols = [];
            cols.push({field: 'name', title: '名称'});
            cols.push({
                field: 'id', title: '查询', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<button type="button" style="margin-left: 10px;" class="btn btn-xs btn-info tooltips"  data-placement="top" data-original-title="查看" onclick="projectBuild.prototype.findData(' + row.id + ',\'building\')"><i class="fa fa-search fa-white"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="引用" onclick="projectBuild.prototype.quote(' + row.id + ')"><i class="fa fa-check"></i></button>';
                    str += '<button type="button" onclick="projectBuild.prototype.removeData(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                    str += '<i class="fa fa-minus"></i>';
                    str += '</button>';

                    str += '</div>';
                    return str;
                }
            });
            $("#" + projectBuild.prototype.config().table).bootstrapTable('destroy');
            TableInit(projectBuild.prototype.config().table, "${pageContext.request.contextPath}/basicAlternativeCase/getBasicAlternativeCaseList", cols, {
                name: $("#queryBuildName").val(),
                tbType: "building",
                planDetailsId:'${planDetailsId}'
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        findData: function (id, buildType, estateId) {
            infoDetail(id, buildType);
        },
        removeData: function (id) {
            AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/basicAlternativeCase/deleteDataById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除数据成功");
                            projectBuild.prototype.loadDataList();
                        }
                        else {
                            AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                })
            })
        },
        quote: function (id) {
            $.ajax({
                url: '${pageContext.request.contextPath}/basicBuilding/quoteFromAlternative',
                data: {
                    id: id,
                    tableId:'${tbId}'
                },
                type: "get",
                success: function (result) {
                    if (result.ret) {
                        if (result.data != null) {
                            autocompleteData(result.data);
                            $('#' + projectBuild.prototype.config().box).modal("hide");
                        }
                    } else {
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                }
            })
        }
    }

    //单元
    var projectUnit = function () {
    };
    projectUnit.prototype = {
        config: function () {
            var data = {};
            data.table = "projectUnitTable";
            data.box = "divBoxProjectUnit";
            return data;
        },
        showModel: function (buildingId) {
            projectUnit.prototype.loadDataList();
            $('#' + projectUnit.prototype.config().box).modal("show");
        },
        loadDataList: function () {
            var cols = [];
            cols.push({field: 'name', title: '名称'});
            cols.push({
                field: 'id', title: '查询', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<button type="button" style="margin-left: 10px;" class="btn btn-xs btn-info tooltips"  data-placement="top" data-original-title="查看" onclick="projectUnit.prototype.findData(' + row.id + ',\'unit\')"><i class="fa fa-search fa-white"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="引用" onclick="projectUnit.prototype.quote(' + row.id + ')"><i class="fa fa-check"></i></button>';
                    str += '<button type="button" onclick="projectUnit.prototype.removeData(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                    str += '<i class="fa fa-minus"></i>';
                    str += '</button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + projectUnit.prototype.config().table).bootstrapTable('destroy');
            TableInit(projectUnit.prototype.config().table, "${pageContext.request.contextPath}/basicAlternativeCase/getBasicAlternativeCaseList", cols, {
                name: $("#queryUnitName").val(),
                tbType: "unit",
                planDetailsId:'${planDetailsId}'
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
                    url: "${pageContext.request.contextPath}/basicAlternativeCase/deleteDataById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除数据成功");
                            projectUnit.prototype.loadDataList();
                        }
                        else {
                            AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                })
            })
        },
        findData: function (id, buildType, estateId) {
            infoDetail(id, buildType);
        },
        quote: function (id) {
            $.ajax({
                url: '${pageContext.request.contextPath}/basicUnit/quoteFromAlternative',
                data: {
                    id: id,
                    tableId:'${tbId}'
                },
                type: "get",
                success: function (result) {
                    if (result.ret) {
                        if (result.data != null) {
                            autocompleteData(result.data);
                            $('#' + projectUnit.prototype.config().box).modal("hide");
                        }
                    } else {
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                }
            })
        }
    }

    //房屋
    var projectHouse = function () {
    };
    projectHouse.prototype = {
        config: function () {
            var data = {};
            data.table = "projectHouseTable";
            data.box = "divBoxProjectHouse";
            return data;
        },
        showModel: function (unitId) {
            projectHouse.prototype.loadDataList();
            $('#' + projectHouse.prototype.config().box).modal("show");
        },
        loadDataList: function () {
            var cols = [];
            cols.push({field: 'name', title: '名称'});
            cols.push({
                field: 'id', title: '查询', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<button type="button" style="margin-left: 10px;" class="btn btn-xs btn-info tooltips"  data-placement="top" data-original-title="查看" onclick="projectHouse.prototype.findData(' + row.id + ',\'unit\')"><i class="fa fa-search fa-white"></i></button>';
                    str += '<button type="button" style="margin-left: 5px;" class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="引用" onclick="projectHouse.prototype.quote(' + row.id + ')"><i class="fa fa-check"></i></button>';
                    str += '<button type="button" onclick="projectHouse.prototype.removeData(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                    str += '<i class="fa fa-minus"></i>';
                    str += '</button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + projectHouse.prototype.config().table).bootstrapTable('destroy');
            TableInit(projectHouse.prototype.config().table, "${pageContext.request.contextPath}/basicAlternativeCase/getBasicAlternativeCaseList", cols, {
                name: $("#queryHouseName").val(),
                tbType: "house",
                planDetailsId:'${planDetailsId}'
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
                    url: "${pageContext.request.contextPath}/basicAlternativeCase/deleteDataById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            notifySuccess("成功", "删除数据成功");
                            projectHouse.prototype.loadDataList();
                        }
                        else {
                            AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                })
            })
        },
        findData: function (id, buildType, estateId) {
            infoDetail(id, buildType);
        },
        quote: function (id) {
            $.ajax({
                url: '${pageContext.request.contextPath}/basicHouse/quoteFromAlternative',
                data: {
                    id: id,
                    tableId:'${tbId}'
                },
                type: "get",
                success: function (result) {
                    if (result.ret) {
                        if (result.data != null) {
                            autocompleteData(result.data);
                            $('#' + projectHouse.prototype.config().box).modal("hide");
                        }
                    } else {
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                }
            })
        }
    }
</script>

<%--引用案例库信息--%>
<div id="caseEstateModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">楼盘</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmEstateQuery" class="form-horizontal">
                    <input type="hidden" id="id" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                省
                                            </label>
                                            <div class="col-sm-2">
                                                <select id="queryProvince"
                                                        class="form-control input-full search-select select2">
                                                </select>
                                            </div>
                                            <label class="col-sm-1 control-label">
                                                市
                                            </label>
                                            <div class="col-sm-2">
                                                <select id="queryCity"
                                                        class="form-control input-full search-select select2">
                                                </select>
                                            </div>
                                            <label class="col-sm-1 control-label">楼盘名称</label>
                                            <div class="col-sm-2">
                                                <input type="text" class="form-control input-full" id="querySearch"/>
                                            </div>
                                            <div class="col-sm-2">
                                                <button style="margin-left: 10px" class="btn btn-info  btn-sm"
                                                        type="button"
                                                        onclick="caseFun.caseEstate.find()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                                    查询
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <table class="table table-bordered" id="caseEstateTable">
                                        </table>
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
<div id="caseBuildModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">楼栋</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <table class="table table-bordered" id="caseBuildTable">
                                        </table>
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
<div id="caseUnitModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">单元</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <table class="table table-bordered" id="caseUnitTable">
                                        </table>
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
<div id="caseHouseModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">房屋</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <table class="table table-bordered" id="caseHouseTable">
                                        </table>
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
<script type="text/javascript">
    var CaseFunFun = function () {

    };
    CaseFunFun.prototype.config = {
        father: {
            caseEstate: {
                frm: function () {
                    return "frmEstateQuery";//楼盘 frm
                },
                table: function () {
                    return "caseEstateTable";//楼盘 table
                },
                box: function () {
                    return "caseEstateModal";//楼盘 modal
                }
            },
            caseBuild: {
                table: function () {
                    return "caseBuildTable";
                },
                box: function () {
                    return "caseBuildModal";
                }
            },
            caseUnit: {
                table: function () {
                    return "caseUnitTable";
                },
                box: function () {
                    return "caseUnitModal";
                }
            },
            caseHouse: {
                table: function () {
                    return "caseHouseTable";
                },
                box: function () {
                    return "caseHouseModal";
                }
            }
        }
    };

    CaseFunFun.prototype.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    }

    CaseFunFun.prototype.event = {};
    var caseFun = new CaseFunFun();

    caseFun.caseEstate = {
        /**
         * @author:  zch
         * 描述:加载数据列表
         * @date:2018-09-13
         **/
        loadDataList: function (flag, estateName) {
            if (estateName) {
                $("#querySearch").val(estateName);
            }
            var estate = {};
            estate.querySearch = $("#querySearch").val();
            estate.queryProvince = $("#queryProvince").val();
            estate.queryCity = $("#queryCity").val();
            if (!flag) {
                estate = {querySearch: null, queryCity: null, queryProvince: null};
            }
            var cols = [];
            cols.push({field: 'name', title: '名称'});
            cols.push({
                field: 'area', title: '区域', formatter: function (value, row, index) {
                    return AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
                }
            });
            cols.push({field: 'blockName', title: '版块'});
            cols.push({field: 'averagePrice', title: '均价'});
            cols.push({field: 'coverAnArea', title: '占地面积'});
            cols.push({field: 'version', title: '版本'});
            cols.push({
                field: 'id', title: '查询', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<button type="button" class="btn btn-xs btn-info tooltips"  data-placement="top" data-original-title="详情" onclick="caseFun.caseEstate.findData(' + row.id + ')"><i class="fa fa-search fa-white"></i></button>';
                    str += '<button type="button" class="btn btn-xs btn-warning tooltips" style="margin-left: 5px;"  data-placement="top" data-original-title="引用" onclick="caseFun.caseEstate.quote(' + row.id + ')"><i class="fa fa-check"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + caseFun.config.father.caseEstate.table()).bootstrapTable('destroy');
            TableInit(caseFun.config.father.caseEstate.table(), "${pageContext.request.contextPath}/basicEstate/getCaseEstateVos", cols, {
                name: estate.querySearch,
                city: estate.queryCity,
                province: estate.queryProvince
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });

        },
        find: function () {
            var itemVal = $("#" + caseFun.config.father.caseEstate.frm()).find("[name='search']").val();
            caseFun.caseEstate.loadDataList(true);
        },
        /**
         * @author:  zch
         * 描述:详情数据
         * @date:2018-09-13
         **/
        findData: function (id) {
            var href = "${pageContext.request.contextPath}/basicEstate/detailView";
            href += "?id=" + id;
            window.open(href, "");
        },
        showModel: function (estateName) {
            AssessCommon.initAreaInfo({
                provinceTarget: $("#queryProvince"),
                cityTarget: $("#queryCity")
            });
            $("#" + caseFun.config.father.caseEstate.frm()).clearAll();
            caseFun.caseEstate.loadDataList(true, estateName);
            $('#' + caseFun.config.father.caseEstate.box()).modal("show");
        },
        quote: function (id) {
            $.ajax({
                url: '${pageContext.request.contextPath}/basicEstate/quoteCaseEstate',
                data: {
                    sourceId: id,
                    targetId:'${tbId}'
                },
                type: "get",
                success: function (result) {
                    if (result.ret) {
                        if (result.data != null) {
                            estateCommon.initById(result.data.id);
                            $('#' + caseFun.config.father.caseEstate.box()).modal('hide');
                        }
                    } else {
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                }
            })
        }
    };


    caseFun.caseBuild = {
        findData: function (id) {
            var href = "${pageContext.request.contextPath}/basicBuilding/detailView";
            href += "?id=" + id;
            window.open(href, "");
        },
        loadDataList: function (estateId, buildName) {
            var cols = [];
            cols.push({field: 'buildingNumber', title: '楼栋编号'});
            cols.push({field: 'buildingName', title: '楼栋名称'});
            cols.push({field: 'version', title: '版本'});
            cols.push({
                field: 'id', title: '查询', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<button type="button" class="btn btn-xs btn-info tooltips"  data-placement="top" data-original-title="查看" onclick="caseFun.caseBuild.findData(' + row.id + ',\'tb_List\')"><i class="fa fa-search fa-white"></i></button>';
                    str += '<button type="button" class="btn btn-xs btn-warning tooltips" style="margin-left: 5px;"  data-placement="top" data-original-title="引用" onclick="caseFun.caseBuild.quote(' + row.id + ')"><i class="fa fa-check"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + caseFun.config.father.caseBuild.table()).bootstrapTable('destroy');
            TableInit(caseFun.config.father.caseBuild.table(), "${pageContext.request.contextPath}/basicBuilding/getCaseBuildingList", cols, {
                estateId: estateId,
                buildName: buildName
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        showModel: function (estateId, buildName) {
            caseFun.caseBuild.loadDataList(estateId, buildName);
            $('#' + caseFun.config.father.caseBuild.box()).modal("show");
        },
        quote: function (id) {
            $.ajax({
                url: '${pageContext.request.contextPath}/basicBuilding/quoteCaseBuilding',
                data: {
                    sourceId: id,
                    targetId:${tbId}
                },
                type: "get",
                success: function (result) {
                    if (result.ret) {
                        if (result.data != null) {
                            buildingCommon.initById(result.data.id);
                            $('#' + caseFun.config.father.caseBuild.box()).modal('hide');
                        }
                    } else {
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                }
            })
        }
    };


    caseFun.caseUnit = {
        findData: function (id) {
            var href = "${pageContext.request.contextPath}/basicUnit/detailView";
            href += "?id=" + id;
            window.open(href, "");
        },
        loadDataList: function (buildingId, unitName) {
            var cols = [];
            cols.push({field: 'unitNumber', title: '单元编号'});
            cols.push({field: 'elevatorHouseholdRatio', title: '梯户比'});
            cols.push({field: 'version', title: '版本'});
            cols.push({
                field: 'id', title: '查询', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<button type="button" class="btn btn-xs btn-info tooltips"  data-placement="top" data-original-title="查看" onclick="caseFun.caseUnit.findData(' + row.id + ',\'tb_List\')"><i class="fa fa-search fa-white"></i></button>';
                    str += '<button type="button" class="btn btn-xs btn-warning tooltips" style="margin-left: 5px;"  data-placement="top" data-original-title="引用" onclick="caseFun.caseUnit.quote(' + row.id + ')"><i class="fa fa-check"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + caseFun.config.father.caseUnit.table()).bootstrapTable('destroy');
            TableInit(caseFun.config.father.caseUnit.table(), "${pageContext.request.contextPath}/basicUnit/getCaseUnitList", cols, {
                buildingId: buildingId,
                unitName: unitName
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        showModel: function (buildingId, unitName) {
            caseFun.caseUnit.loadDataList(buildingId, unitName);
            $('#' + caseFun.config.father.caseUnit.box()).modal("show");
        },
        quote: function (id) {
            $.ajax({
                url: '${pageContext.request.contextPath}/basicUnit/quoteCaseUnit',
                data: {
                    sourceId: id,
                    targetId:${tbId}
                },
                type: "get",
                success: function (result) {
                    if (result.ret) {
                        if (result.data != null) {
                            unitCommon.initById(result.data.id);
                            $('#' + caseFun.config.father.caseUnit.box()).modal('hide');
                        }
                    } else {
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                }
            })
        }
    };

    caseFun.caseHouse = {
        findData: function (id) {
            var href = "${pageContext.request.contextPath}/basicHouse/detailView";
            href += "?id=" + id;
            window.open(href, "");
        },
        loadDataList: function (unitId, houseName) {
            var cols = [];
            cols.push({field: 'houseNumber', title: '房号'});
            cols.push({field: 'floor', title: '所在楼层'});
            cols.push({field: 'version', title: '版本'});
            cols.push({
                field: 'id', title: '查询', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<button type="button" class="btn btn-xs btn-info tooltips"  data-placement="top" data-original-title="查看" onclick="caseFun.caseHouse.findData(' + row.id + ',\'tb_List\')"><i class="fa fa-search fa-white"></i></button>';
                    str += '<button type="button" class="btn btn-xs btn-warning tooltips" style="margin-left: 5px;" data-placement="top" data-original-title="引用" onclick="caseFun.caseHouse.quote(' + row.id + ')"><i class="fa fa-check"></i></button>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + caseFun.config.father.caseHouse.table()).bootstrapTable('destroy');
            TableInit(caseFun.config.father.caseHouse.table(), "${pageContext.request.contextPath}/basicHouse/getCaseHouseList", cols, {
                unitId: unitId,
                houseName: houseName
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        showModel: function (unitId, houseName) {
            caseFun.caseHouse.loadDataList(unitId, houseName);
            $('#' + caseFun.config.father.caseHouse.box()).modal("show");
        },
        quote: function (id) {
            $.ajax({
                url: '${pageContext.request.contextPath}/basicHouse/quoteCaseHouse',
                data: {
                    sourceId: id,
                    targetId:${tbId}
                },
                type: "get",
                success: function (result) {
                    if (result.ret) {
                        if (result.data != null) {
                            houseCommon.initById(result.data.id);
                            $('#' + caseFun.config.father.caseHouse.box()).modal('hide');
                        }
                    } else {
                        AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                    }
                }
            })
        }
    };

</script>
