<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--工作事项列表窗口--%>
<div id="commPlanDetailsListModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">工作事项</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <table class="table table-bordered" id="tbCommPlanDetailsList"></table>
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
    var commPlanDetail = {};

    //显示工作事项弹窗
    commPlanDetail.showPlanDetailsListModal = function (planId, projectPhaseId) {
        commPlanDetail.loadPlanDetailsList(planId,projectPhaseId);
        $('#commPlanDetailsListModal').modal();
    }

    //加载工作事项列表
    commPlanDetail.loadPlanDetailsList = function (planId, projectPhaseId) {
        var cols = [];
        cols.push({field: 'id', title: 'id', visible: false});
        cols.push({
            field: 'projectPhaseName', title: '名称', width: '35%', formatter: function (value, row, index) {
                var str = row.projectPhaseName;
                if (row.planRemarks) {
                    str += "<span style='font-size: 10px;'>(" + row.planRemarks + ")</span>";
                }
                return str;
            }
        });
        cols.push({field: 'executeUserName', title: '责任人', width: '15%'});
        cols.push({
            field: 'status', title: '状态', formatter: function (value, row, index) {
                var str = "";
                switch (value) {
                    case "runing": {
                        str = "<span class='label label-info'>" + "进行中" + "</span>";
                        break;
                    }
                    case "finish": {
                        str = "<span class='label label-success'>" + "已完成" + "</span>";
                        break;
                    }
                    case "close": {
                        str = "<span class='label label-warning'>" + "关闭" + "</span>";
                        break;
                    }
                    case "none": {
                        str = "<span class='label label-default'>" + row.projectPhaseName + "</span>";
                        break;
                    }
                }
                return str;
            }
        });
        cols.push({
            field: 'planStartDate', title: '开始日期', width: '10%', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        cols.push({
            field: 'planEndDate', title: '结束日期', width: '10%', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        cols.push({
            field: 'opt', title: '操作', width: '7%', formatter: function (value, row, index) {
                var str = "";
                str += "<a href='${pageContext.request.contextPath}/ProjectTask/projectTaskDetailsById?planDetailsId="+row.id+"' target='_blank' style='margin-left: 5px;' title='查看' class='btn btn-xs btn-info'  ><i class='fa fa-search fa-white'></i></a>";
                return str;
            }
        });
        var select = {
            projectPhaseId: projectPhaseId,
            planId: planId
        };
        $('#tbCommPlanDetailsList').bootstrapTable('destroy');
        TableInit($('#tbCommPlanDetailsList'), "${pageContext.request.contextPath}/projectInfo/getPlanDetailListByProjectPhaseId", cols,select, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }
</script>
