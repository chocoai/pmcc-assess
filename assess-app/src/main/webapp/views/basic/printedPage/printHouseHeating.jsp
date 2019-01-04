<%--
 供暖情况
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    供暖情况
    <table class="table table-bordered" id="HouseHeatingList">
    </table>
</div>

<script type="application/javascript">
    $(function () {
        houseHeating.loadDataDicList();
    })
    var houseHeating={};
    houseHeating.loadDataDicList=function () {
        var cols = commonColumn.houseHeatingColumn();
        $("#HouseHeatingList").bootstrapTable('destroy');
        TableInit("HouseHeatingList", "${pageContext.request.contextPath}/basicHouseEquipment/getBootstrapTableVo", cols, {
            type: "houseHeating",
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





