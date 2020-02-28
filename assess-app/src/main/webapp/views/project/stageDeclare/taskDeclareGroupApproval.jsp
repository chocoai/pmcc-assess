<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/tree-grid/css/jquery.treegrid.css">
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
                        <div class="card full-height">
                            <div class="card-header collapse-link">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        申报分组
                                    </div>
                                    <div class="card-tools">
                                        <button class="btn  btn-link btn-primary btn-xs"><span
                                                class="fa fa-angle-down"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="declareApplyForm">

                                    <input type="hidden" name="id" value="${declare.id}">
                                    <input type="hidden" name="planDetailsId" value="${projectPlanDetails.id}">
                                    <input type="hidden" name="projectId" value="${projectPlanDetails.projectId}">

                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <table id="tb_project_declare_group_table" class="table table-bordered">
                                            </table>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <%@include file="/views/share/form_approval.jsp" %>
                    <%@include file="/views/share/form_log.jsp" %>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>


</body>

<script>


</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/tree-grid/js/jquery.treegrid.js?v=${assessVersion}"></script>


<script>

    var declareRecordGroup = {};

    declareRecordGroup.groupTargetTable = $("#tb_project_declare_group_table");
    declareRecordGroup.groupTargetModel = $("#tb_project_declare_group_model");
    declareRecordGroup.groupDispatchModel = $("#tb_project_declare_group_dispatch_model");
    declareRecordGroup.sureDeclareRecordTable = $("#sureDeclareRecordTableList");
    declareRecordGroup.cancelDeclareRecordTable = $("#cancelDeclareRecordTableList");


    /*申报记录 table list*/
    declareRecordGroup.baseDeclareRecordTable = function (table, data) {
        var cols = [];
//        cols.push({checkbox: true, width: "5%"});
        cols.push({field: 'name', title: '权证名称', width: "12%"});
        cols.push({field: 'buildingNumber', title: '楼栋号', width: "6%"});
        cols.push({field: 'unit', title: '单元号', width: "6%"});
        cols.push({field: 'ownership', title: '所有权人', width: "6%"});
        cols.push({field: 'seat', title: '坐落', width: "9%"});
        cols.push({field: 'floorArea', title: '证载面积', width: "9%"});
        cols.push({field: 'practicalArea', title: '实际面积', width: "9%"});
        var method = {
            showColumns: true,
            showRefresh: true,
            search: false,
            onLoadSuccess: function () {//加载成功时执行

            },
            onLoadError: function () {

            }
        };
        table.bootstrapTable('destroy');
        TableInit(table.attr("id"), "${pageContext.request.contextPath}/declareRecord/getBootstrapTableVoDispatch", cols, data, method);
    };


    declareRecordGroup.loadTable = function () {
        var cols = [];
//        cols.push({checkbox: true, width: "5%"});
        cols.push({
            field: 'provinceName', title: '区域', formatter: function (value, row, index) {
                return AssessCommon.getAreaFullName(row.provinceName, row.cityName, row.districtName);
            }, width: "15%"
        });
        cols.push({field: 'name', title: '名称', width: "6%"});
        cols.push({
            field: 'id', title: '分组查看', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success" href="javascript:declareRecordGroup.dispatchData(' + value + ');" >分组操作</i></a>';
                str += "<i class='fa fa-hand-o-right' aria-hidden='true'></i>";
                str += '</div>';
                return str;
            }, width: "10%"
        });
        cols.push({field: 'remarks', title: '备注', width: "35%"});
        var method = {
            showColumns: true,
            showRefresh: true,
            search: false,
            onLoadSuccess: function () {//加载成功时执行

            },
            onLoadError: function () {

            }
        };
        var quarm = {
            projectId: '${projectInfo.id}',
            planId: '${projectPlan.id}'
        };
        declareRecordGroup.groupTargetTable.bootstrapTable('destroy');
        TableInit(declareRecordGroup.groupTargetTable.attr("id"), "${pageContext.request.contextPath}/declareRecordGroup/getBootstrapTableVo", cols, quarm, method);
        var bootstrapTable = declareRecordGroup.groupTargetTable.closest(".bootstrap-table");
        if (bootstrapTable.size() != 0) {
            var fixedTableToolbar = bootstrapTable.find(".fixed-table-toolbar");
            if (fixedTableToolbar.size() != 0) {
                fixedTableToolbar.append($("#projectDeclareToolbar").html());
            }
        }
    };

    /*申报记录 查询*/
    declareRecordGroup.selectRecordData = function (_this, groupId) {
        var select = {};
        var target = $(_this).closest('.x-valid');
        select.planId = '${projectPlan.id}';
        select.projectId = '${projectInfo.id}';
        var name = target.find("[name='name']").val();
        if (name) {
            select.name = name;
        }
        var buildingNumber = target.find("[name='buildingNumber']").val();
        if (buildingNumber) {
            select.buildingNumber = buildingNumber;
        }
        var unit = target.find("[name='unit']").val();
        if (unit) {
            select.unit = unit;
        }
        if (groupId) {
            select.groupId = groupId;
            declareRecordGroup.baseDeclareRecordTable($(declareRecordGroup.sureDeclareRecordTable.selector), select);
        } else {
            declareRecordGroup.baseDeclareRecordTable($(declareRecordGroup.cancelDeclareRecordTable.selector), select);
        }
    };


    /**
     * 显示分组model
     */
    declareRecordGroup.dispatchData = function (groupId) {
        var target = $(declareRecordGroup.groupDispatchModel.selector) ;
        target.modal("show");
        var data = {};
        data.planId = '${projectPlan.id}';
        data.projectId = '${projectInfo.id}';
        var obj = JSON.parse(JSON.stringify(data));
        data.groupId = groupId;
        target.find("[name='groupId']").val(groupId);
        declareRecordGroup.baseDeclareRecordTable(  $(declareRecordGroup.cancelDeclareRecordTable.selector) , obj);
        declareRecordGroup.baseDeclareRecordTable(  $(declareRecordGroup.sureDeclareRecordTable.selector), data);
    };

    $(document).ready(function () {
        declareRecordGroup.loadTable();
    });

</script>

<script type="application/javascript">
    //提交审批
    function saveform() {
        saveApprovalform("");
    }

</script>


<div id="tb_project_declare_group_dispatch_model" class="modal fade bs-example-modal-lg" data-backdrop="static"
     tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">申报分组</h3>
            </div>
            <form class="form-horizontal">
                <input type="hidden" name="groupId">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                            <div class="panel-body">
                                <div class="form-group">

                                    <div class="x-valid ">
                                        <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                            <input type="text" placeholder="权证名称" name="name" class="form-control">
                                        </div>
                                        <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                            <input type="text" placeholder="楼栋号" name="buildingNumber"
                                                   class="form-control">
                                        </div>
                                        <div class="col-xs-1  col-sm-1  col-md-1  col-lg-1">
                                            <input type="text" placeholder="单元号" name="unit" class="form-control">
                                        </div>
                                        <div class="col-xs-1  col-sm-1  col-md-1  col-lg-1">
                                            <input type="button" class="btn btn-primary" value="查询"
                                                   onclick="declareRecordGroup.selectRecordData(this,null);">
                                        </div>
                                    </div>


                                    <div class="x-valid ">
                                        <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                            <input type="text" placeholder="权证名称" name="name" class="form-control">
                                        </div>
                                        <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                            <input type="text" placeholder="楼栋号" name="buildingNumber"
                                                   class="form-control">
                                        </div>
                                        <div class="col-xs-1  col-sm-1  col-md-1  col-lg-1">
                                            <input type="text" placeholder="单元号" name="unit" class="form-control">
                                        </div>
                                        <div class="col-xs-1  col-sm-1  col-md-1  col-lg-1">
                                            <input type="button" class="btn btn-primary" value="查询"
                                                   onclick="declareRecordGroup.selectRecordData(this,$(this).closest('form').find('[name=groupId]').val());">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <div class="col-sm-5">
                                            <table class="table table-bordered" id="cancelDeclareRecordTableList">
                                                <caption>未分组</caption>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="x-valid">
                                        <div class="col-xs-5  col-sm-5  col-md-5  col-lg-5">
                                            <table class="table table-bordered" id="sureDeclareRecordTableList">
                                                <caption>已经分组</caption>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        关闭
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>

