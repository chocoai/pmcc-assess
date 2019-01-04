<%--
排水情况
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    排水情况
    <table class="table table-bordered" id="HouseWaterDrainList">
    </table>
</div>
<script type="application/javascript">
    $(function () {
        houseWaterDrain.loadDataDicList();
    })
    var houseWaterDrain = {};
    houseWaterDrain.loadDataDicList = function () {
        var cols = commonColumn.houseWaterDrainColumn();
        $("#HouseWaterDrainList").bootstrapTable('destroy');
        TableInit("HouseWaterDrainList", getContextPath() + "/basicHouseWaterDrain/getBootstrapTableVo", cols, {
            houseId: '${empty basicHouse.id?0:basicHouse.id}'
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            pagination: false,
            pageSize: 100,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };


</script>


</html>

