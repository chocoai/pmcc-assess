
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<div class="x_panel" id="industrySurface">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>屋面结构 <label class="control-label ExamineBuildingSurfaceList"></label></h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="ExamineBuildingSurfaceList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>


<script>
    function examineBuildingSurfaceList() {
        var cols = commonColumn.buildingSurfaceColumn();
        $("#ExamineBuildingSurfaceList").bootstrapTable('destroy');
        TableInit('ExamineBuildingSurfaceList', "${pageContext.request.contextPath}/basicBuildingSurface/getBootstrapTableVo", cols, {
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
        $('#ExamineBuildingSurfaceList').closest('.x_panel').find('.x_title').bind('click', function () {
            examineBuildingSurfaceList();
        })
    });

</script>