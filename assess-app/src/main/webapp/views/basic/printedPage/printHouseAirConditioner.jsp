<%--
 空调情况
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    空调情况
    <table class="table table-bordered" id="HouseAirConditionerList">
    </table>
</div>

<script type="application/javascript">
    $(function () {
        houseAirConditioner.loadDataDicList();
    })
    var houseAirConditioner = {};
    houseAirConditioner.loadDataDicList = function () {
        var cols = commonColumn.houseAirConditionerColumn();
        $("#HouseAirConditionerList").bootstrapTable('destroy');
        TableInit("HouseAirConditionerList", "${pageContext.request.contextPath}/basicHouseEquipment/getBootstrapTableVo", cols, {
            type: "houseAirConditioner",
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


