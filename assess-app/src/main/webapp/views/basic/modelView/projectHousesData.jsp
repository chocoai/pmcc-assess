<%@ page contentType="text/html;charset=UTF-8" language="java" %>


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
                                <div>
                                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                        楼盘名称
                                    </label>
                                    <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                        <input type="text" data-rule-maxlength="50"
                                               placeholder="楼盘名称" id="queryEstateName" name="queryEstateName"
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
<script type="application/javascript">
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
            return data;
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
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="查勘及案例" onclick="projectData.prototype.checkCaseData(' + index + ')">查勘及案例</a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + projectData.prototype.config().table).bootstrapTable('destroy');
            TableInit(projectData.prototype.config().table, "${pageContext.request.contextPath}/projectCenter/getProjectList", cols, {
                queryName: $("#queryName").val(),
                queryEstateName: $("#queryEstateName").val()
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
            $("#" + projectData.prototype.config().frm).clearAll();
            projectData.prototype.loadProjectDataList();
            $('#' + projectData.prototype.config().box).modal("show");
        },
        checkCaseData: function (index) {
            var row = $("#projectDataList").bootstrapTable('getData')[index];
            projectData.prototype.loadProjectCaseItemList(row.id, row.projectCategoryId);
            $('#' + projectData.prototype.config().itemBox).modal("show");
        },
        loadProjectCaseItemList: function (projectId, projectCategoryId) {
            var cols = [];
            cols.push({field: 'projectPhaseName', title: '名称'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    str = "<a target='_blank' href='${pageContext.request.contextPath}/ProjectTask/projectTaskDetailsById?planDetailsId=" + row.id + "' data-placement='top' data-original-title='查看详情' class='btn btn-xs btn-info tooltips' >查看详情</a>";
                    str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="引用" onclick="projectData.prototype.autocompleteData(' + index + ');" >引用</a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + projectData.prototype.config().itemTable).bootstrapTable('destroy');
            TableInit(projectData.prototype.config().itemTable, "${pageContext.request.contextPath}/basicApply/getProjectCaseItemList", cols, {
                projectId: projectId,
                projectCategoryId: projectCategoryId,
                basicApplyTypeId:"${empty basicApplyTypeId?type:basicApplyTypeId}"
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $(".tooltips").tooltip();
                }
            });
        },
        autocompleteData:function (index) {
            if(${!empty basicApplyTypeId}){
                basicApplyIndex.autocompleteData(index);
            }
            if(${!empty type}){
                fillInformation.autocompleteData(index);
            }
        }
    }
</script>