<%--
 新风情况
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    新风情况
    <table class="table table-bordered" id="HouseNewWindList">
    </table>
</div>


<script type="application/javascript">
    $(function () {
        houseNewWind.loadDataDicList();
    })
    var houseNewWind={};
    houseNewWind.loadDataDicList=function () {
        var cols = commonColumn.houseNewWindColumn();
        $("#HouseNewWindList").bootstrapTable('destroy');
        TableInit("HouseNewWindList", "${pageContext.request.contextPath}/basicHouseEquipment/getBootstrapTableVo", cols, {
            type: "houseNewWind",
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





