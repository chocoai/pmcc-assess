
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>建筑功能 <label class="control-label examineBuildingFunctionList"></label></h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="examineBuildingFunctionList">
        </table>
    </div>
</div>

<script>

    function examineBuildingFunctionList() {
        var cols = commonColumn.buildingFunctionColumn();
        $("#examineBuildingFunctionList").bootstrapTable('destroy');
        TableInit('examineBuildingFunctionList', "${pageContext.request.contextPath}/basicBuildingFunction/getBootstrapTableVo", cols, {
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
        $('#examineBuildingFunctionList').closest('.x_panel').find('.x_title').bind('click', function () {
            examineBuildingFunctionList();
        })
    });
</script>
