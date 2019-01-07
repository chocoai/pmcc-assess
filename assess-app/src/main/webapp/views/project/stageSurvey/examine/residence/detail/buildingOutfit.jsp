
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>楼栋外装 <label class="control-label ExamineBuildingOutfitList"></label></h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="ExamineBuildingOutfitList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script>
    function examineBuildingOutfitList() {
        var cols = commonColumn.buildingOutfitColumn();
        $("#ExamineBuildingOutfitList").bootstrapTable('destroy');
        TableInit("ExamineBuildingOutfitList", "${pageContext.request.contextPath}/basicBuildingOutfit/getBootstrapTableVo", cols, {
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
        $('#ExamineBuildingOutfitList').closest('.x_panel').find('.x_title').bind('click', function () {
            examineBuildingOutfitList();
        })
    });
</script>
