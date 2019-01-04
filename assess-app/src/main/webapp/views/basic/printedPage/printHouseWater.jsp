<%--
 供排水情况
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    供水情况
    <table class="table table-bordered" id="HouseWaterList">
    </table>
</div>
<script type="application/javascript">
    $(function () {
        houseWater.loadDataDicList();
    })
    var houseWater = {};
    houseWater.loadDataDicList = function () {
        var cols = commonColumn.houseWaterColumn();
        $("#HouseWaterList").bootstrapTable('destroy');
        TableInit("HouseWaterList", "${pageContext.request.contextPath}/basicHouseWater/getBootstrapTableVo", cols, {
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
    }
</script>


</html>

