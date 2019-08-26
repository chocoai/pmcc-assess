<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--引用项目中的楼盘--%>
<div id="divBoxProjectData" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">项目</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <form id="frmQuery" class="form-horizontal">
                            <div class="form-group ">
                                <div>
                                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                        项目名称
                                    </label>
                                    <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                        <input type="text" data-rule-maxlength="50"
                                               placeholder="项目名称" id="queryName" name="queryName"
                                               class="form-control">
                                    </div>
                                </div>
                                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                    <button type="button" class="btn btn-primary"
                                            onclick="projectData.prototype.loadProjectDataList()">
                                        查询
                                    </button>
                                </div>
                            </div>

                        </form>
                        <table class="table table-bordered" id="projectDataList">
                            <!-- cerare document add ajax data-->
                        </table>

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>
<div id="divBoxProjectItemData" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">查勘及案例</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <table class="table table-bordered" id="projectCaseItemList">
                            <!-- cerare document add ajax data-->
                        </table>

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>
<div id="divBoxTree" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">楼盘信息</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <form id="frmTree" class="form-horizontal">
                            <div class="form-group ">
                                <a class="btn btn-xs btn-default" onclick="treeTool.informationDetail();">
                                    查看信息
                                </a>
                                <a class="btn btn-xs btn-warning" onclick=" treeTool.quote();" id="showQuoteBut"
                                   style="display: none">
                                    引用
                                </a>
                            </div>
                            <div class="col-md-3">
                                <ul id="ztree" class="ztree"></ul>
                            </div>
                            <div class="col-md-9">
                            </div>
                        </form>

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>
<script type="application/javascript">
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
            data.treeBox = "divBoxTree";
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
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="查勘及案例" onclick="projectData.prototype.checkTaskData(' + index + ')">查勘及案例</a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + projectData.prototype.config().table).bootstrapTable('destroy');
            TableInit(projectData.prototype.config().table, "${pageContext.request.contextPath}/projectCenter/getProjectList", cols, {
                queryName: $("#queryName").val(),
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
                    str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="查看信息" onclick="projectData.prototype.findData(' + row.id + ')"><i class="fa fa-search fa-white"></i></a>';
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
        findData: function (id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/basicApplyBatch/getDataByPlanDetailsId",
                data: {planDetailsId: id},
                type: "get",
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                        if (result.data) {
                            treeTool.ztreeInit(result.data);
                            treeTool.showQuoteBut();
                            $('#' + projectData.prototype.config().treeBox).modal("show");
                        } else {
                            alert("没有楼盘数据")
                        }
                    }
                }
            })
        },
    };

</script>
<script type="text/javascript">
    var batchApply = undefined;
    var setting = {
        data: {
            key: {
                name: "displayName"
            },
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pid",
                rootPId: 0
            }
        },
        async: {
            enable: true,
            url: "${pageContext.request.contextPath}/basicApplyBatch/getTree",
            otherParam: {
                estateId: function () {
                    return batchApply.estateId;
                }
            },
            autoParam: ["id=pid"]
        },
        // 回调函数
        callback: {
            onClick: function (event, treeId, treeNode, clickFlag) {
                treeTool.showQuoteBut();
            }
        }
    };

    var treeTool = function () {

    };

    //初始化tree
    treeTool.ztreeInit = function (data) {
        batchApply = data;
        zTreeObj = $.fn.zTree.init($("#ztree"), setting, [{
            "id": 0,
            "pid": 0,
            "displayName": data.estateName,
            "isParent": true
        }]);
        //展开第一级，选中根节点
        var rootNode = zTreeObj.getNodes()[0];
        zTreeObj.selectNode(rootNode);

        zTreeObj.expandNode(rootNode, true, false, true);
    }


    //进入填写信息页面
    treeTool.informationDetail = function () {
        var node = zTreeObj.getSelectedNodes()[0];
        var estateId = 0;
        if (node.id == 0) {
            estateId = batchApply.estateId;
        }
        var type = batchApply.type;
        window.open('${pageContext.request.contextPath}/basicApplyBatch/informationDetail?type=' + type + "&id=" + node.id + "&buildingType=" + node.level + "&estateId=" + estateId);
    }


    //显示引用按钮
    treeTool.showQuoteBut = function () {
        var node = zTreeObj.getSelectedNodes()[0];
        var level = node.level;
        if (level == 3) {
            $("#showQuoteBut").show();
        } else {
            $("#showQuoteBut").hide();
        }
    }

    //引用
    treeTool.quote = function (id) {
        basicApplyIndex.autocompleteData(id);
    }
</script>
