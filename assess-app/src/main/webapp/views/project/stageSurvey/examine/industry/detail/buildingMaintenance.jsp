
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel" id="industryMaintenance">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>围护结构 <label class="control-label ExamineBuildingMaintenanceList"></label></h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="ExamineBuildingMaintenanceList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script>

    function examineBuildingMaintenanceList() {
        var cols = commonColumn.buildingMaintenanceColumn();
        $("#ExamineBuildingMaintenanceList").bootstrapTable('destroy');
        TableInit('ExamineBuildingMaintenanceList', "${pageContext.request.contextPath}/basicBuildingMaintenance/getBootstrapTableVo", cols, {
            buildingId: buildingCommon.getBuildingId(),
            approval:true
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }

    $(function () {
        //绑定事件
        $('#ExamineBuildingMaintenanceList').closest('.x_panel').find('.x_title').bind('click', function () {
            examineBuildingMaintenanceList();
        })

    });
</script>
