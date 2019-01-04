<%--
 临街（路）状况
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    临街（路）
    <table class="table table-bordered" id="HouseFaceStreetList">
    </table>
</div>

<script type="application/javascript">
    $(function () {
        houseFaceStreet.loadDataDicList();
    })
    var houseFaceStreet = {};
    houseFaceStreet.loadDataDicList = function () {
        var cols = commonColumn.houseFaceStreetColumn();
        $("#HouseFaceStreetList").bootstrapTable('destroy');//examineHouseFaceStreet
        TableInit("HouseFaceStreetList", "${pageContext.request.contextPath}/basicHouseFaceStreet/getBootstrapTableVo", cols, {
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


